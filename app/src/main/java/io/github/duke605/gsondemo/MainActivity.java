package io.github.duke605.gsondemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

import io.github.duke605.gsondemo.task.TaskReadApi;

public class MainActivity extends AppCompatActivity {

    public EditText index;
    public Button button;
    public TextView name, versions, dependencies, authors, desc, link, types, numMods;
    public Mod[] mods = new Mod[0];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        index = (EditText) findViewById(R.id.index);
        button = (Button) findViewById(R.id.get_mod);

        name = (TextView) findViewById(R.id.name);
        versions = (TextView) findViewById(R.id.versions);
        dependencies = (TextView) findViewById(R.id.dependencies);
        authors = (TextView) findViewById(R.id.authors);
        desc = (TextView) findViewById(R.id.desc);
        link = (TextView) findViewById(R.id.link);
        types = (TextView) findViewById(R.id.types);
        numMods = (TextView) findViewById(R.id.num_mods);

        new TaskReadApi(this).execute("http://modlist.mcf.li/api/v3/all.json");

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int index = Integer.parseInt(MainActivity.this.index.getText().toString());
                Mod mod;

                if (index < 0 || index > mods.length - 1) {
                    Toast.makeText(MainActivity.this, "Out of bounds", Toast.LENGTH_LONG).show();
                    return;
                }

                mod = mods[index];

                // Setting text view
                name.setText(mod.name);
                desc.setText(mod.desc);
                link.setText(mod.link);
                versions.setText(Arrays.toString(mod.versions).replaceAll("[\\[\\]]", ""));
                dependencies.setText(Arrays.toString(mod.dependencies).replaceAll("[\\[\\]]", ""));
                authors.setText(Arrays.toString(mod.author).replaceAll("[\\[\\]]", ""));
                types.setText(Arrays.toString(mod.type).replaceAll("[\\[\\]]", ""));
            }
        });
    }
}
