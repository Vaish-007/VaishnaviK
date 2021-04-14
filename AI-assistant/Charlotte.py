import pyttsx3 
import speech_recognition as sr 
import datetime
import wikipedia 
import webbrowser
import os
import smtplib

engine = pyttsx3.init('sapi5')
voices = engine.getProperty('voices')
engine.setProperty('voice', voices[0].id)


def speak(audio):
    engine.say(audio)
    engine.runAndWait()


def wishMe():
    hour = int(datetime.datetime.now().hour)
    if hour>=0 and hour<12:
        speak("Good Morning!")

    elif hour>=12 and hour<18:
        speak("Good Afternoon!")

    else:
        speak("Good Evening!")

    speak("I am Charlotte. Please tell me how may I help you")

def takeCommand():
    #It takes microphone input from the user and returns string output

    r = sr.Recognizer()
    with sr.Microphone() as source:
        print("Listening...")
        r.pause_threshold = 1
        audio = r.listen(source)

    try:
        print("Recognizing...")
        query = r.recognize_google(audio, language='en-in')
        print(f"User said: {query}\n")

    except Exception as e:
        # print(e)
        print("Say that again please...")
        speak("Say that again please")
        return "None"
    return query

def sendEmail(to, content):   #enable the less secure apps feature in your Gmail account
    server = smtplib.SMTP('smtp.gmail.com', 587)
    server.ehlo()
    server.starttls()
    server.login('SenderMail-id@gmail.com', 'password')
    server.sendmail('SenderMail-id@gmail.com', to, content)
    server.close()

if __name__ == "__main__":
    wishMe()
    while True:
    # if 1:
        query = takeCommand().lower()

        # Logic for executing tasks based on query
        if 'wikipedia' in query:
            speak('Searching Wikipedia...')
            query = query.replace("wikipedia", "")
            results = wikipedia.summary(query, sentences=2)
            speak("According to Wikipedia")
            print(results)
            speak(results)

        elif 'open youtube' in query:
            speak("Opening Youtube!")
            webbrowser.open("youtube.com")

        elif 'open google' in query:
            speak("Opening Google!")
            webbrowser.open("google.com")

        elif 'open stack overflow' in query:
            speak("Opening StackOverflow!")
            webbrowser.open("stackoverflow.com")

        elif 'open geeksforgeeks' in query:
            speak("Opening Geeksforgeeks!")
            webbrowser.open("geeksforgeeks.org")


        elif 'play music' in query:
            music_dir = 'C:\\Users\\vaishnavi\\Music\\Playlists'
            songs = os.listdir(music_dir)
            print(songs)
            os.startfile(os.path.join(music_dir, songs[0]))

        elif 'the time' in query:
            strTime = datetime.datetime.now().strftime("%H:%M:%S")
            speak(f"The time is {strTime}")

        elif 'open code' in query:
            codePath = "C:\\Users\\vaishnavi\\AppData\\Local\\Programs\\Microsoft VS Code\\Code.exe"
            os.startfile(codePath)

        elif 'email to vaishnavi' in query:
            try:
                speak("What should I say?")
                content = takeCommand()
                to = "ReceiverMail-id@gmail.com"
                sendEmail(to, content)
                speak("Email has been sent!")
            except Exception as e:
                print(e)
                speak("Sorry my friend vaishnavi. I am not able to send this email")

        elif 'how are you' in query:
            speak('I am good. Thanks for asking. What can I do for you')

        elif 'you are amazing' in query:
            speak('It takes someone amazing to say something is amazing. You are amazing too!')

        elif 'quit' in query:
            exit()
