package fr.rushcubeland.aletheia.json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.util.Iterator;

public final class JSONWriter implements Closeable {

    private final BufferedWriter writer;
    private int space;

    public JSONWriter(String path) throws IOException
    {
        this(new File(path));
    }

    public JSONWriter(File file) throws IOException
    {
        this(new FileWriter(file));
    }

    public JSONWriter(Writer writer)
    {
        this(new BufferedWriter(writer));
    }

    public JSONWriter(BufferedWriter writer)
    {
        this.writer = writer;
    }

    public void write(JSONArray array) throws IOException
    {
        writer.write("[");
        writer.newLine();

        this.space+=2;
        String space = spaceBuilder();

        for(int i = 0; i < array.length(); i++)
        {
            Object object = null;
            try {
                object = array.get(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            if(object instanceof Number || object instanceof Boolean) writer.write(space+object);
            else if(object instanceof JSONObject) write((JSONObject) object, true);
            else if(object instanceof JSONArray) write((JSONArray) object);
            else writer.write(space+"\""+object.toString()+"\"");

            if(i < array.length()-1) writer.write(",");
            writer.newLine();
        }

        this.space-=2;
        space = spaceBuilder();

        writer.write(space+"]");
    }

    private void write(JSONObject jsonObject, boolean spacing) throws IOException
    {
        writer.write((spacing ? spaceBuilder() : "") + "{");
        writer.newLine();

        this.space+=2;
        String space = spaceBuilder();

        int i = 0;
        final int max = jsonObject.length();

        for (Iterator<String> it = jsonObject.keys(); it.hasNext(); ) {
            String key = it.next();
            writer.write(space+"\""+key+"\":");
            Object object = null;
            try {
                object = jsonObject.get(key);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            if(object instanceof Number || object instanceof Boolean) writer.write(object.toString());
            else if(object instanceof JSONObject) write((JSONObject) object, false);
            else if(object instanceof JSONArray) write((JSONArray) object);
            else writer.write("\""+ (object != null ? object.toString() : null) +"\"");

            if(i < max-1) writer.write(",");
            i++;

            writer.newLine();
        }

        this.space-=2;
        space = spaceBuilder();

        writer.write(space+"}");
    }

    public void write(JSONObject jsonObject) throws IOException
    {
        write(jsonObject, false);
    }

    private String spaceBuilder()
    {
        StringBuilder builder = new StringBuilder();
        builder.append(" ".repeat(Math.max(0, space)));
        return builder.length() == 0 ? "" : builder.toString();
    }

    public void flush() throws IOException
    {
        writer.flush();
    }

    public void close() throws IOException
    {
        writer.close();
    }
}
