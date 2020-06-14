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

package com.jalasoft.practice.controller;

import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

@RestController
@RequestMapping("/convert")
public class ConverterController {

    @PostMapping
    public String convertVideo(@RequestParam(value="video") MultipartFile video) {
        String filePath = System.getProperty("user.dir") + "/converted/"
                + Objects.requireNonNull(video.getOriginalFilename());
        File convertedFile = new File(filePath);

        try {
            FFmpeg ffmpeg = new FFmpeg(filePath);
            FFprobe ffprobe = new FFprobe("/path/to/ffprobe");

            String test = convertedFile.getAbsolutePath();
            String test1 = convertedFile.getCanonicalPath();
            FFmpegBuilder builder = new FFmpegBuilder()
                    .setInput(convertedFile.getAbsolutePath())     // Filename, or a FFmpegProbeResult
                    .overrideOutputFiles(true) // Override the output if it exists

                    .addOutput(filePath)   // Filename for the destination
                    .setFormat("wmv")        // Format is inferred from filename, or can be set
                    .setTargetSize(250_000)  // Aim for a 250KB file

                    .disableSubtitle()       // No subtiles

                    .setAudioChannels(1)         // Mono audio
                    .setAudioCodec("aac")        // using the aac codec
                    .setAudioRate(48_000)  // at 48KHz
                    .setAudioRate(32768)      // at 32 kbit/s

                    .setVideoCodec("libx264")     // Video using x264
                    .setVideoFramerate(24)     // at 24 frames per second
                    .setVideoResolution(640, 480) // at 640x480 resolution

                    .setStrict(FFmpegBuilder.Strict.EXPERIMENTAL) // Allow FFmpeg to use experimental specs
                    .done();

            FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);

            // Run a one-pass encode
            executor.createJob(builder).run();

            // Or run a two-pass encode (which is better quality at the cost of being slower)
            executor.createTwoPassJob(builder).run();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Hello";
    }
}