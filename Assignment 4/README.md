# ASSIGNMENT #4  

In this assignment, you will will be implementing a variety of static methods. You will obtain additional practice creating and using arrays.  

**Audio Effects**

You are tasked with completing a command line utility that lets users mix and mash audio files. Using simple starting sounds, users can create a masterpiece of audacious audio. Using the utility, users can cut out sections of sound, add leading and trailing silence, add echoes, merge sounds, reverse sounds, and loop sounds. Lucky a previous developer has already implemented the main() method and a handful of the utility's effects (play, stats, and gain). Your task is to complete methods for the reverse, loop, merge, echo, cut and pad audio effects. Here is an [example mix](https://katie.cs.mtech.edu/classes/archive/f13/csci135/assign/audio/kdv26.wav) created from this [set of commands](https://katie.cs.mtech.edu/classes/archive/f13/csci135/assign/audio/kdv.txt).	

To start with, download the [audio.zip](https://katie.cs.mtech.edu/classes/archive/f13/csci135/assign/audio/audio.zip) file and unzip it to your project directory. This file contains the existing source code AudioEffect.java as well as a selection of audio files for your testing pleasure. You will be using [StdAudio.java](StdAudio.java) to handle the loading and playback of audio. StdAudio provides access to the audio samples in a WAV format audio file via a double [] array. All your audio manipulating methods will operate by manipulating and returning double [] arrays. You should not change the double [] array passed as input to your methods. Rather you should create a new double [] array, fill it with values, and return it from each method (for an example see the audioGain() method).

Each audio sample in a file is representing by a double value, typically between -1.0 and +1.0. Values outside this range are clipped to -1.0 or +1.0 during playback. The bigger the absolute value of the double value, the louder the sound is at that point in the file. All the provided audio files are recorded at 44100 samples per second so as to work with StdAudio. If want to try your program with your own audio files, make sure they are WAV format, recorded at 44100 samples per second, 16-bit, mono, signed format. If you want to convert files in a different format, various programs such as [GoldWave](http://www.goldwave.com/) can do this.

The provided AudioEffect.java file is partially completed. You need to fill in the method bodies for audioReverse(), audioLoop(), audioMerge(), and audioCut(). You need to add all of the audioPad() and audioEcho() methods as well as the calls to audioPad and audioEcho in the main method. Before each method, you'll find a detailed comment describing what the method does, its parameters, and its return values. Users of your utility may not always make the most sensible requests (e.g. asking to loop an audio clip -43 times). Your program should defend itself against such silly requests and not crash. In the case of bad input to your method, simply return an appropriate failure value as specified by the method comment (typically null).

The first example usage below shows the command line arguments taken by the program. The first argument is always the filename of the WAV file. The second argument is always one of the effect names given as a string (e.g. "play", "stats", "reverse", "gain"). After this, there are zero or more additional arguments depending on the type of effect.

**What is this null value?** Variables of reference type (which includes arrays) can be assigned a value of null. A variable set to null refers to no object (though the variable could refer to an object in the future). We use it in this assignment to allow our methods to inform the caller via the double [] return result that something went wrong and no audio result is available.

**How do I convert from a time in seconds to a sample index?** All the audio in this assignment is sampled at 44100 samples per second (declared as a constant SAMPLING_RATE in AudioEffect.java). If you multiply the time in seconds by SAMPLING_RATE you will get the sample number. In our reference solution, we dropped the decimal portion by casting this calculated value to an int. In general, you should avoid putting "magic numbers" such as 44100 in your code and instead use a declared constant such as SAMPLING_RATE. This is better style as the name tells the reader what it means and also makes it easy to change the value everywhere by one simple change.

**Example usage:**  

% java AudioEffects  
AudioEffects <infile> <effect> [options]  

Effects:  
  play                                - play the file  
  stats                               - print some stats about the audio  
  reverse                 <out file>  - reverse an audio file  
  gain    <factor>        <out file>  - change the volume by given factor  
  loop    <times>         <out file>  - loop audio given number of times  
  merge   <infile2>       <out file>  - merge two audio files together  
  cut     <start> <end>   <out file>  - clip audio from [start, end] seconds  
  pad     <start> <end>   <out file>  - add silence to start and/or end  
  echo    <secs> <factor> <out file>  - add an echo to audio  

Supports only 44.1 kHz mono 16-bit signed WAV audio files.  

**Reverse:**  

% java AudioEffects piano.wav reverse piano_reverse.wav  
Effect: reverse  
Wrote 226800 samples to piano_reverse.wav  

% java AudioEffects piano_reverse.wav stats  
Effect: stats  
Samples          :       226800  
Length (s)       :     5.142857  
Average level    :     0.000044  
Max level        :     0.434431  
Below 1% max (%) :     3.113316  
Below 5% max (%) :    15.427690  

**Loop:**  

% java AudioEffects scratch.wav loop 4 scratch_loop4.wav  
Effect: loop  
Wrote 282036 samples to scratch_loop4.wav  

% java AudioEffects scratch_loop4.wav stats  
Effect stats  
Samples          :       282036  
Length (s)       :     6.395374  
Average level    :    -0.001621  
Max level        :     1.000000  
Below 1% max (%) :    34.224000  
Below 5% max (%) :    59.749819  

% java AudioEffects scratch.wav loop -43 scratch_loop-43.wav  
Effect: loop  

**Merge:**  

% java AudioEffects cow.wav merge chimes.wav cow_chimes.wav  
Effect: merge  
Wrote 275015 samples to cow_chimes.wav  

% java AudioEffects cow_chimes.wav stats  
Effect: stats  
Samples          :       275015  
Length (s)       :     6.236168  
Average level    :    -0.000075  
Max level        :     0.388287  
Below 1% max (%) :    28.735524  
Below 5% max (%) :    65.232442  

% java AudioEffects beatbox.wav merge harp.wav beatbox_harp.wav  
Effect: merge  
Wrote 89491 samples to beatbox_harp.wav  

% java AudioEffects beatbox_harp.wav stats  
Effect: stats  
Samples          :        89491  
Length (s)       :     2.029274  
Average level    :     0.000768  
Max level        :     0.509171  
Below 1% max (%) :    25.547820  
Below 5% max (%) :    63.730431  

**Cut:**

% java AudioEffects dialup.wav cut 4.0 5.0 dialup_cut4-5.wav  
Effect: cut  
Wrote 44100 samples to dialup_cut4-5.wav  

% java AudioEffects dialup_cut4-5.wav stats  
Effect: stats  
Samples          :        44100  
Length (s)       :     1.000000  
Average level    :     0.017001  
Max level        :     0.324198  
Below 1% max (%) :     3.269841  
Below 5% max (%) :    24.467120  

% java AudioEffects dialup.wav cut 4.0 50.0 dialup_cut4-50.wav  
Effect: cut  

% java AudioEffects dialup.wav cut 5.0 4.0 dialup_cut5-4.wav  
Effect: cut  

**Pad:**  

% java AudioEffects buzzer.wav pad 1.0 2.0 buzzer_pad1-2.wav  
Effect: pad  
Wrote 216969 samples to buzzer_pad1-2.wav  

% java AudioEffects buzzer_pad1-2.wav stats  
Effect: stats  
Samples          :       216969  
Length (s)       :     4.919932  
Average level    :    -0.000004  
Max level        :     0.997925  
Below 1% max (%) :    62.104725  
Below 5% max (%) :    66.364319  

% java AudioEffects buzzer.wav pad -10.0 -20.0 buzzer_pad-10-20.wav  
Effect: pad  

**Echo:** 

% java AudioEffects singer.wav echo 0.5 0.6 singer_echo_0.5_0.6.wav  
Effect: echo  
Wrote 680597 samples to singer_echo_0.5_0.6.wav  

% java AudioEffects singer_echo_0.5_0.6.wav stats  
Effect: stats  
Samples          :       680597  
Length (s)       :    15.433039  
Average level    :    -0.000015  
Max level        :     0.965667  
Below 1% max (%) :    32.031290  
Below 5% max (%) :    54.441321  

---

This README was adapted from an assignment page at Montana Tech: https://katie.cs.mtech.edu/classes/archive/f13/csci135/assign/audio/
