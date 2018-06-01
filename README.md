The Emojify Project is Lesson 3 of Udacity's Advanced Android learning path. The overarching purpose of this project is to exemplify the usefulness and importance of incorporating external libraries into projects. The Emojify Project uses an Android facial recognition API - Mobile Vision - which includes features to identify faces, determine the location of landmarks (mouth, nose, eyes) and detect the probability of certain states of these landmarks (e.g., are the eye(s) open or closed, is the mouth smiling or frowning).

What Emojify does is to match a face or series of faces with the emoji(s) that most closely resemble the appearance of the face(s).

The initial commit contains the starter code and comments that will be built upon.

The second commit adds a gradle dependency "com.google.android.gms:play-services-vision:11.0.1" under the mobile>app folder in order to load the "mobile vision" api into the app. The latest api available is "com.google.android.gms:play-services-vision:15.0.2" however trial and error showed that adding any version after 11 resulted in difficulties with dependencies; perhaps the later versions of "mobile vision" are not compatible with SDK 26 yet.

This third commit creates a new Java Class called Emojifier which calls API functions to read in a photo, create a FaceDetector object, assign its attributes, and create and use a detector to determine the number of faces in the photo.