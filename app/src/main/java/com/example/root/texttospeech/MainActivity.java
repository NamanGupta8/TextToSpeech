package com.example.root.texttospeech;

import
        android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.w3c.dom.Text;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {
private TextToSpeech tts;
private Button btn;
private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tts=new TextToSpeech(this,this);
        btn=findViewById(R.id.button);
        editText=findViewById(R.id.editText);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOut();
            }
        });

    }

@Override
public void onDestroy() {
if(tts!=null){
    tts.stop();
    tts.shutdown();
}
    super.onDestroy();
}
    @Override
    public void onInit(int status) {
            if(status==TextToSpeech.SUCCESS){
                int result= tts.setLanguage(Locale.US);
                if(result== TextToSpeech.LANG_MISSING_DATA || result==TextToSpeech.LANG_NOT_SUPPORTED){
                    Log.e("TTS","This language is not supported");

                } else {
                    btn.setEnabled(true);
                }

            }else {
                Log.e("TTS","Initialization Failed !!");
            }

    }

    private void speakOut(){
         String text=editText.getText().toString();
         tts.speak(text,TextToSpeech.QUEUE_FLUSH,null);
    }


}

