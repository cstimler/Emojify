The Emojify Project is Lesson 3 of Udacity's Advanced Android learning path. The overarching purpose of this project is to exemplify the usefulness and importance of incorporating external libraries into projects. The Emojify Project uses an Android facial recognition API - Mobile Vision - which includes features to identify faces, determine the location of landmarks (mouth, nose, eyes) and detect the probability of certain states of these landmarks (e.g., are the eye(s) open or closed, is the mouth smiling or frowning).

What Emojify does is to match a face or series of faces with the emoji(s) that most closely resemble the appearance of the face(s).

The initial commit contains the starter code and comments that will be built upon.

The second commit adds a gradle dependency "com.google.android.gms:play-services-vision:11.0.1" under the mobile>app folder in order to load the "mobile vision" api into the app. The latest api available is "com.google.android.gms:play-services-vision:15.0.2" however trial and error showed that adding any version after 11 resulted in difficulties with dependencies; perhaps the later versions of "mobile vision" are not compatible with SDK 26 yet.

The third commit creates a new Java Class called Emojifier which calls API functions to read in a photo, create a FaceDetector object, assign its attributes, and create and use a detector to determine the number of faces in the photo.

The fourth commit now applies the new methods to identify the number of faces in an image, and for each image prints out the state of each face's eyes (open or not) and mouth (smiling or not). For each classification call it provides a 0<float<1 number as a probability. A sparse array of "Faces" holds the result of the detector; in order to iterate over it (SparseArray has somewhat different methods than other ArrayLists) one should use the .ValueAt() method instead of the .get() method (which I learned the hard way). I'm frankly not too impressed with the accuracy of this face detector (it seems I never smile ;-( ), but one could custom train a convolutional neural net and use that instead (see Google documentation on TensorFlow lite).

The fifth commit establishes an empiric baseline for thresholds for eye and mouth states based on the developer's (admittedly anecdotal) experience, and selects the appropriate emoji through an if/then (I did switch) series of statements; the appropriate emoji to be used is printed to the logcat output.

The sixth commit superimposes the image of the chosen emoji(s) over the location of the face(s), carefully matching each face with its appropriate emoji.  

This seventh commit is a challenge to incorporate the external "Butter Knife" library and replace all View and "findViewById" calls with data binding, and replace all onClick XML methods with @OnClick binding in MainActivity.