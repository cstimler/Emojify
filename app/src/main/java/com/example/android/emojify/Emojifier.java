/*
* Copyright (C) 2017 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*  	http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.example.android.emojify;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.util.SparseArray;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;

import static com.example.android.emojify.Emojifier.Emoji.Frown;
import static com.example.android.emojify.Emojifier.Emoji.FrownEyesClosed;
import static com.example.android.emojify.Emojifier.Emoji.FrownLeftWink;
import static com.example.android.emojify.Emojifier.Emoji.FrownRightWink;
import static com.example.android.emojify.Emojifier.Emoji.Smile;
import static com.example.android.emojify.Emojifier.Emoji.SmileEyesClosed;
import static com.example.android.emojify.Emojifier.Emoji.SmileLeftWink;
import static com.example.android.emojify.Emojifier.Emoji.SmileRightWink;

class Emojifier {

    private static final String LOG_TAG = Emojifier.class.getSimpleName();

    /**
     * Method for detecting faces in a bitmap.
     *
     * @param context The application context.
     * @param picture The picture in which to detect the faces.
     */
    static void detectFaces(Context context, Bitmap picture) {

        // Create the face detector, disable tracking and enable classifications
        FaceDetector detector = new FaceDetector.Builder(context)
                .setTrackingEnabled(false)
                .setClassificationType(FaceDetector.ALL_CLASSIFICATIONS)
                .build();

        // Build the frame
        Frame frame = new Frame.Builder().setBitmap(picture).build();

        // Detect the faces
        SparseArray<Face> faces = detector.detect(frame);

        // Log the number of faces
        Log.d(LOG_TAG, "detectFaces: number of faces = " + faces.size());

        // If there are no faces detected, show a Toast message
        if(faces.size() == 0){
            Toast.makeText(context, R.string.no_faces_message, Toast.LENGTH_SHORT).show();
        } else {
            for (int i = 0; i < faces.size(); ++i) {
                Face face = faces.valueAt(i);

                // Log the classification probabilities for each face.
                whichEmoji(face);
                // TODO (6): Change the call to getClassifications to whichEmoji() to log the appropriate emoji for the facial expression.
            }

        }


        // Release the detector
        detector.release();
    }


    /**
     * Method for logging the classification probabilities.
     *
     * @param face The face to get the classification probabilities.
     */
    private static void whichEmoji(Face face){
        // TODO (2): Change the name of the getClassifications() method to whichEmoji() (also change the log statements)
        // Log all the probabilities
        Log.d(LOG_TAG, "whichEmoji: smilingProb = " + face.getIsSmilingProbability());
        Log.d(LOG_TAG, "whichEmoji: leftEyeOpenProb = "
                + face.getIsLeftEyeOpenProbability());
        Log.d(LOG_TAG, "whichEmoji: rightEyeOpenProb = "
                + face.getIsRightEyeOpenProbability());
        final double thresholdSmiling = 0.3;
        final double thresholdLeftEyeOpen = 0.4;
        final double thresholdRightEyeOpen = 0.4;
        // TODO (3): Create threshold constants for a person smiling, and and eye being open by taking pictures of yourself and your friends and noting the logs.
        boolean bSmiling = false;
        if (face.getIsSmilingProbability() > thresholdSmiling) {bSmiling = true;}
        boolean bLeftEyeClosed = true;
        if (face.getIsLeftEyeOpenProbability() > thresholdLeftEyeOpen) {bLeftEyeClosed = false;}
        boolean bRightEyeClosed = true;
        if (face.getIsRightEyeOpenProbability() > thresholdRightEyeOpen) {bRightEyeClosed = false;}
        // TODO (4): Create 3 boolean variables to track the state of the facial expression based on the thresholds you set in the previous step: smiling, left eye closed, right eye closed.
        int classifyFaces = 0;
        if (bSmiling) classifyFaces = 100;
        if (bLeftEyeClosed) classifyFaces += 10;
        if (bRightEyeClosed) classifyFaces += 1;

        switch(classifyFaces) {
            case(0): Log.d("Emoji: ", Frown.toString());
            break;
            case(1): Log.d("Emoji: ", FrownRightWink.toString());
            break;
            case(10): Log.d("Emoji ", FrownLeftWink.toString());
            break;
            case(100): Log.d("Emoji: ", Smile.toString());
            break;
            case(11): Log.d("Emoji: ", FrownEyesClosed.toString());
            break;
            case(101): Log.d("Emoji: ", SmileRightWink.toString());
            break;
            case(110): Log.d("Emoji: ", SmileLeftWink.toString());
            break;
            case(111): Log.d("Emoji: ", SmileEyesClosed.toString());
            break;
            default:
                break;
        }
        // TODO (5): Create an if/else system that selects the appropriate emoji based on the above booleans and log the result.
    }


    // TODO (1): Create an enum class called Emoji that contains all the possible emoji you can make (smiling, frowning, left wink, right wink, left wink frowning, right wink frowning, closed eye smiling, close eye frowning).
    enum Emoji {
        Smile, SmileLeftWink, SmileRightWink, SmileEyesClosed, Frown, FrownLeftWink, FrownRightWink, FrownEyesClosed
    }

}
