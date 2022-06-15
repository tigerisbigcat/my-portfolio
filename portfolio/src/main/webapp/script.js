// Copyright 2020 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

/**
 * Adds a random greeting to the page.
 */
function addRandomGreeting() {
  const greetings =
      ['Hello friends!', '¡Hola Amigas!', '你好，朋友！', 'Bonjour Copains!'];

  // Pick a random greeting.
  const greeting = greetings[Math.floor(Math.random() * greetings.length)];

  // Add it to the page.
  const greetingContainer = document.getElementById('greeting-container');
  greetingContainer.innerText = greeting;
}

/** Fetches the current date from the server and adds it to the page. */
async function showServerTime() {
    const responseFromServer = await fetch('/date');
    const textFromResponse = await responseFromServer.text() + "  You are on my sever :)";
  
    const dateContainer = document.getElementById('date-container');
    dateContainer.innerText = textFromResponse;
  }

async function jsonFile() {
    // Send a request to /my-data-url.
    const responseFromServer = await fetch('/form-handler');

    // Parse the response as JSON.
    // const myObject = await responseFromServer.text() + "test";
    const textFromResponse = await responseFromServer.text() + "test";

    const dateContainer = document.getElementById('date-container-json');
    dateContainer.innerText = textFromResponse;

    // // Now we can reference the fields in myObject!
    // console.log(myObject.x);
    // console.log(myObject.y);
    // console.log(myObject.z);
}