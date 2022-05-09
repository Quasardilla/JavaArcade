package UI;

import java.io.File;
import java.io.IOException;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundLoader {
    
    protected Clip sound;

    public SoundLoader(URL fileName)
    {
        AudioInputStream audio;
        try {
            audio = AudioSystem.getAudioInputStream(fileName);
            sound = AudioSystem.getClip();
            sound.open(audio);
        } catch (IOException | LineUnavailableException e1) {} catch (UnsupportedAudioFileException e1) {}
    }

    public SoundLoader(File file)
    {
        AudioInputStream audio;
        try {
            audio = AudioSystem.getAudioInputStream(file);
            sound = AudioSystem.getClip();
            sound.open(audio);
        } catch (IOException | LineUnavailableException e1) {} catch (UnsupportedAudioFileException e1) {}
    }

    public Clip get()
    {
        return sound;
    }

}
