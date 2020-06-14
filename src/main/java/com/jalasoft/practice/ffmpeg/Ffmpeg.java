/*
 *
 *   Copyright (c) 2020 Jalasoft.
 *
 *  This software is the confidential and proprietary information of Jalasoft.
 *  ("Confidential Information"). You shall not disclose such Confidential
 *  Information and shall use it only in accordance with the terms of the
 *  license agreement you entered into with Jalasoft.
 *
 */

package com.jalasoft.practice.ffmpeg;

import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;
import net.bramp.ffmpeg.probe.FFmpegProbeResult;

import java.io.File;
import java.io.IOException;

/**
 * Video Converter class.
 */
public class Ffmpeg {

    /**
     * Convert video to a given output format.
     */
    public void convertFile(File convertedFile, String newDestination,
                            String outputFormat) throws IOException {
        FFmpeg ffmpeg = new FFmpeg(System.getProperty("user.dir") + "/ffmpeg/bin/ffmpeg.exe");
        FFprobe ffprobe = new FFprobe(System.getProperty("user.dir") + "/ffmpeg/bin/ffprobe.exe");
        FFmpegProbeResult probeResult = ffprobe.probe(convertedFile.getAbsolutePath());

        FFmpegBuilder builder = new FFmpegBuilder()
                .setInput(probeResult)
                .overrideOutputFiles(true)
                .addOutput(newDestination)
                .setFormat(outputFormat)
                .setTargetSize(2500_000)
                .setAudioChannels(1)
                .setAudioCodec("aac")
                .setAudioRate(48_000)
                .setAudioRate(44100)
                .setVideoCodec("libx264")
                .setVideoFramerate(24)
                .setVideoResolution(640, 480)
                .setStrict(FFmpegBuilder.Strict.EXPERIMENTAL)
                .done();

        FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);
        executor.createTwoPassJob(builder).run();
    }
}
