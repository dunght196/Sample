package dunght.example.com.sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import dunght.example.com.sqlite.db.note.NoteDataSource;
import dunght.example.com.sqlite.model.Note;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button button;
    private TextView textView;
    String listNote;
//    ArrayList<>

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText)findViewById(R.id.editText);
        button = (Button)findViewById(R.id.button);
        textView = (TextView)findViewById(R.id.textView);

        final NoteDataSource noteDataSource = new NoteDataSource(this);


        listNote = getlistNote(noteDataSource.getAllNote());
        textView.setText(listNote);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String note = editText.getText().toString();
                String datetime = DateFormat.getDateTimeInstance().format(new Date());
                Note n = new Note(note, datetime);
                noteDataSource.addNote(n);
                listNote = getlistNote(noteDataSource.getAllNote());
                textView.setText(listNote);
            }
        });
    }

    public String getlistNote(ArrayList<Note> listNote) {
        StringBuilder builder = new StringBuilder();
        for(Note note : listNote) {
            builder.append(note.getNote()).append(" : ").append(note.getDate()).append("\n");
        }
        return builder.toString();
    }
}
