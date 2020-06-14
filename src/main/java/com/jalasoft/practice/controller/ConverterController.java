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

import com.jalasoft.practice.ffmpeg.Ffmpeg;
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
    public String convertVideo(@RequestParam(value="outputFormat") String outputFormat,
                               @RequestParam(value="destination") String destination,
                               @RequestParam(value="newFileName") String newFileName,
                               @RequestParam(value="video") MultipartFile video) {
        String originalName = video.getOriginalFilename();
        String status;
        String filePath = System.getProperty("user.dir") + "/converted/"
                + Objects.requireNonNull(originalName);
        File convertedFile = new File(filePath);
        Ffmpeg ffmpeg = new Ffmpeg();

        try {
            String newName = newFileName.equals("") ? originalName.substring(0, originalName.lastIndexOf('.')) :
                    newFileName;
            String newDestination = (destination.equals("")) ? System.getProperty("user.dir") + "/converted/"
                    + newName + "." + outputFormat : destination + "." + outputFormat;
            status = "Success convert video " + originalName + " to ." + outputFormat
                    + " format. Saved in " + newDestination;
            if (!originalName.endsWith(outputFormat)) {
                video.transferTo(convertedFile);
                ffmpeg.convertFile(convertedFile, newDestination, outputFormat);
            }
            else {
                status = "Error, the file " + originalName + " is in " + outputFormat +
                        " format already, it is not needed to convert, please change output format value.";
            }
        } catch (IOException e) {
            status = "Error while trying to convert file.\n" + e.getMessage();
        } catch (NullPointerException e) {
            status = "Error, file could be corrupted or not exist, please select a new valid file.\n" + e.getMessage();
        }

        return status;
    }
}