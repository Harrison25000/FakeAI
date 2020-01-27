import speech_recognition as s_r


print(s_r.__version__) # just to print the version not required
r = s_r.Recognizer()
device_list = s_r.Microphone.list_microphone_names()
print(s_r.Microphone.list_microphone_names())
USB_MICROPHONE_POSITION = s_r.Microphone.list_microphone_names().index('AK5371: USB Audio (hw:1,0)')
MAC_POSITION = 0
my_mic = s_r.Microphone(device_index=USB_MICROPHONE_POSITION) #my device index is 0, you have to put your device index
with my_mic as source:
    print("Say now!!!!")
    r.adjust_for_ambient_noise(source) #reduce noise
    audio = r.listen(source) #take voice input from the microphone
    speechText=r.recognize_google(audio)
    print(speechText) #to print voice into text 


lines = open("/home/pi/Projects/FakeAI/SpeechDetection.txt", "r").readlines()
lines[0]="This is the first line \n"

text_file = open("/home/pi/Projects/FakeAI/SpeechDetection.txt","w")
for line in lines:
    text_file.write(speechText)
text_file.close()