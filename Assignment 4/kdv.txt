java AudioEffects dialup.wav cut 0.0 6.2 kdv1.wav
java AudioEffects kdv1.wav pad 0.0 35 kdv2.wav
java AudioEffects exposure.wav gain 2.0 kdv3.wav
java AudioEffects kdv3.wav cut 1.1 2.4  kdv4.wav
java AudioEffects kdv4.wav loop 3  kdv5.wav
java AudioEffects kdv3.wav cut 1.1 1.8  kdv6.wav
java AudioEffects kdv6.wav loop 3 kdv7.wav
java AudioEffects kdv5.wav pad 6.5 30.0 kdv8.wav
java AudioEffects kdv2.wav merge kdv8.wav kdv9.wav
java AudioEffects kdv9.wav gain 1.2 kdv10.wav
java AudioEffects beatbox.wav loop 11 kdv11.wav 
java AudioEffects kdv11.wav pad 8.5 0.0 kdv12.wav 
java AudioEffects kdv12.wav merge kdv10.wav kdv13.wav
java AudioEffects exposure.wav pad 8.0 20.0 kdv14.wav
java AudioEffects kdv14.wav gain 1.5 kdv15.wav
java AudioEffects kdv13.wav merge kdv15.wav kdv16.wav
java AudioEffects kdv16.wav gain 2.0 kdv17.wav
java AudioEffects cow.wav echo 0.5 0.4 kdv18.wav
java AudioEffects kdv18.wav echo 0.3 0.2 kdv19.wav
java AudioEffects kdv19.wav pad 17.0 20.0 kdv20.wav
java AudioEffects kdv20.wav merge kdv17.wav kdv21.wav
java AudioEffects singer.wav gain 0.5 kdv22.wav
java AudioEffects kdv22.wav echo 1.0 0.15 kdv23.wav
java AudioEffects kdv23.wav echo 0.15 0.3 kdv24.wav
java AudioEffects kdv24.wav pad 19.0 20.0 kdv25.wav
java AudioEffects kdv21.wav merge kdv25.wav kdv26.wav
java AudioEffects kdv26.wav stats
java AudioEffects kdv26.wav play

