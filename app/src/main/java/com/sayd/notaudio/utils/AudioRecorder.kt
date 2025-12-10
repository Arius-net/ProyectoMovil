package com.sayd.notaudio.utils

import android.content.Context
import android.media.MediaRecorder
import android.os.Build
import java.io.File
import java.io.IOException

/**
 * Clase para manejar la grabación de audio
 * RF1: Permite grabar audio y guardarlo en formato MP4
 */
class AudioRecorder(private val context: Context) {
    private var mediaRecorder: MediaRecorder? = null
    private var outputFile: File? = null
    private var isRecording = false
    private var startTime: Long = 0
    
    /**
     * Inicia la grabación de audio
     * @return El archivo donde se está grabando o null si hay error
     */
    fun startRecording(): File? {
        try {
            // Crear archivo temporal para la grabación
            val timestamp = System.currentTimeMillis()
            outputFile = File(context.cacheDir, "recording_$timestamp.mp4")
            
            mediaRecorder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                MediaRecorder(context)
            } else {
                @Suppress("DEPRECATION")
                MediaRecorder()
            }
            
            mediaRecorder?.apply {
                setAudioSource(MediaRecorder.AudioSource.MIC)
                setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
                setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
                setAudioEncodingBitRate(128000)
                setAudioSamplingRate(44100)
                setOutputFile(outputFile?.absolutePath)
                
                prepare()
                start()
                isRecording = true
                startTime = System.currentTimeMillis()
            }
            
            return outputFile
        } catch (e: IOException) {
            e.printStackTrace()
            stopRecording()
            return null
        }
    }
    
    /**
     * Detiene la grabación de audio
     * @return El archivo con la grabación o null si hay error
     */
    fun stopRecording(): File? {
        if (!isRecording) return null
        
        try {
            mediaRecorder?.apply {
                stop()
                release()
            }
            mediaRecorder = null
            isRecording = false
            
            return outputFile
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }
    
    /**
     * Obtiene la duración de la grabación actual en segundos
     */
    fun getRecordingDuration(): Long {
        return if (isRecording) {
            (System.currentTimeMillis() - startTime) / 1000
        } else {
            0
        }
    }
    
    /**
     * Verifica si está grabando actualmente
     */
    fun isRecording(): Boolean = isRecording
    
    /**
     * Libera recursos
     */
    fun release() {
        if (isRecording) {
            stopRecording()
        }
    }
}

