# GrazerTT

### Libraries Used:
- Jetpack Compose - For UI
- Hilt - For DI
- Kotlin Coroutines - For async work
- Datastore - For storage
- Retrofit - For Network
- Coil - For image loading
- JUnit - For tests
- Mockito - Frot tests

### Workflow:
I started development of this task with AuthActivity UI. After created some basic Auth screen UI and ViewModel I understand what exactly data I’ll need operate with, and it was starting point to implement business logic. Following CLEAN architecture - I implemented layer by layer: Use cases, repositories, data sources, models. After that I created Hilt AppModule with describing each dependency I have. When this initial flow was finished, I upgraded a little bit Auth screen, added progress bar, navigation to next screen and so on. After that I start working in the same way with User List screen. But before, I added Interceptor into Rertofit client to add Bearer Token into requests, as I already got it and stored in Auth flow. After finished with User List screen correspondingly - I added one Unit test for User Screen ViewModel.  

### Technical decisions:
Why I used Hilt? Because it pretty straightforward library for DI which has perfect match with Compose and MVVM approach.
Why I used CLEAN architecture? Actually for such kind of tasks - it’s not really necessary, because here is just 2 simple screens. But if consider this task not just like test task, but start of some project - it need to be done from very initial step, because later it will require significant resources to refactor everything to CLEAN. And using CLEAN in big-size apps - necessary of course.
Why I used Kotlin coroutines? It’s the most easiest way to perform some async work. Yes, we have Rx library as well, but from my point of view - Rx should be used only in very particular cases when developer need to operate with big amount of different data flows and combine them in some way. But actually Kotlin Flows already has this functionality as well.
Why I select MVVM as Presentation level pattern? It’s most modern and powerful approach from compatibility with modern Android libraries point of view. We can achieve the same with MVP of course, but in MVVM it came out pf the box. Also we have MVI - but actually there not so much differences with MVVM and if needed in future - it’s not so difficult to migrate from MVVM to MVI.  

### What can be improved:
1. Error handling. First of all it’s Network errors. We can parse proper statuses in network responses to determine error source and to show proper UI to user (Error snackbar, retry button, some message and so on)
2. We can perform navigation with Navigation Jetpack library. It brings easiest way to control navigation across the app, but I decide not to add it here, because we have just one transition between screens.
3. Logout functionality. Just didn’t have a time to add it :)
4. Would be great to have some initial screen (Splash screen) where we can coordinate our next destination: To Auth screen if user not logged in, or token expired. Or to Main screen otherwise.
5. Perform Compose functions optimisation. I almost sure, there some room to reduce recomposition count of composables functions. I didn’t check it, but it pretty easy to detect some unnecessary recompositions in Layout Inspector, or just by logging. 
6. Add pagination for loading User List. We have necessary info in API response, and in case if user amount will be more than 12 (150 for example) - it would be useful for UX
7. Probably need to add some caching list of users (or any data we will receive in future), but I decide to not add it right now, as we need to understand business requirement more clear to understand expectations.
8. Probably would be great to make UI looks like more in Grazer app style :)
9. There are actually endless list of possible improvements, but if we consider all of them - we will have completed and ready product :)
