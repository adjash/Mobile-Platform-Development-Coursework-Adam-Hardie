package com.example.adam.rss;

 /**
 * Created by Adam on 08/03/2018.
 *
 * Student ID:S1436108
 * Adam Hardie
 */

import android.os.AsyncTask;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class RoadWorksPlanParse extends AsyncTask {
    URL url;
    String title;
    String desc;
    String Result;
    ArrayList<String> headlines2 = new ArrayList();
    ArrayList<String> links = new ArrayList();
    @Override
    protected Object doInBackground(Object[] objects) {
        // Initializing instance variables
        try {
            url = new URL("http://trafficscotland.org/rss/feeds/plannedroadworks.aspx");

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(false);
            XmlPullParser xpp = factory.newPullParser();

            // We will get the XML from an input stream
            xpp.setInput(getInputStream(url), "UTF_8");

            boolean insideItem = false;

            // Returns the type of current event: START_TAG, END_TAG, etc..
            int eventType = xpp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG) {

                    if (xpp.getName().equalsIgnoreCase("item")) {
                        insideItem = true;
                    } else if (xpp.getName().equalsIgnoreCase("title")) {
                        if (insideItem)
                            title = (xpp.nextText()); //extract the headline
                    } else if (xpp.getName().equalsIgnoreCase("description")) {
                        if (insideItem)
                            desc = (xpp.nextText());
                            Result = title+"\n" + "÷÷÷÷÷÷÷÷÷÷÷÷÷÷÷÷÷÷÷÷÷÷÷÷÷÷÷÷÷÷"+"\n" + desc +"\n"+ "÷÷÷÷÷÷÷÷÷÷÷÷÷÷÷÷÷÷÷÷÷÷÷÷÷÷÷÷÷÷";
                            links.add(Result);
                    }
                } else if (eventType == XmlPullParser.END_TAG && xpp.getName().equalsIgnoreCase("item")) {
                    insideItem = false;
                }

                eventType = xpp.next(); //move to next element
            }

        } catch (MalformedURLException e) {
            System.out.println("MalformedURL:" + e);
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            System.out.println("XMLPULLPARSEREXCEPTION: " + e);
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IOException" + e);
            e.printStackTrace();
        }
        //System.out.println(headlines2);

        links.remove(0);
        return links;

    }


    public InputStream getInputStream(URL url) {
        try {
            return url.openConnection().getInputStream();
        } catch (IOException e) {
            return null;
        }
    }

    public ArrayList<String> heads()
    {
        return links;
    }

}