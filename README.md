# Android Notes App
## Overview
This is a simple modern notes management application built using the latest Android development technologies and XML layouts.
This app is made to learn and practice, it is not completely finished and some aspects can be improved.
This README serves as a showcase of the app, highlighting its architecture, key features, and the libraries used.

![App Screenshot](images/overview_1.jpg) ![App Screenshot](images/overview_2.jpg)

## Architecture
The app follows the Model-View-ViewModel (MVVM) architecture pattern, separating the user interface (View) from the underlying data (Model) and business logic (ViewModel). Also follows the Clean Code Architecture pattern, which divides the codebase into three layers: Data, Domain, and UI. 

## Features

### Task Management

- Note List: View a list of notes, including titles and brief description.
- Note Details: Access detailed note information.
- Add New Notes: Easily add notes with titles and descriptions.
- Edit Notes: Modify notes by updating titles and descriptions.
- Delete Notes: Remove notes for a clean and organized task list.

### User Experience

- Swipe Actions: Swipe left to reveal action buttons for quick task management. You can touch or swipe right to hide action buttons.
  
[![Image from Gyazo](https://i.gyazo.com/1b802eab57ea4860aa01363dc2bc1a2c.gif)](https://gyazo.com/1b802eab57ea4860aa01363dc2bc1a2c)  

- Search Functionality: Search for specific notes by titles or descriptions.

[![Image from Gyazo](https://i.gyazo.com/80f95d91313b8e287dfc570d99317b08.gif)](https://gyazo.com/80f95d91313b8e287dfc570d99317b08)
  
- Intuitive UI: Intuitive and visually appealing user interface for a seamless experience.

### Libraries Used
- Android Architecture Components: Utilized ViewModel and Room for robust app architecture.
- Hilt (Dagger): Enabled dependency injection for modular and maintainable code.
- Kotlin Coroutines: Facilitated asynchronous programming and background tasks.
- Navigation Component: Simplified navigation and improved user flow.
- Material Design: Followed Material Design guidelines for a modern and visually appealing UI.

## Possible improvements
- Mark notes as completed feature.
- Functional note archive button, right now only the delete button works from the Swipe Actions.
- Tags for notes and to organize them by tags
- And many more features

## License
```xml
MIT License

Copyright (c) 2024 Carlos Yáñez

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

