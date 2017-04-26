package com.test.jsoupparseqsbk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.Document;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity{
    private Button button;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.textView4);
        parse();
    }
    public void parse(){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 爬取糗事百科的数据并显示,从网络获取要在线程中执行。
                 */
                Toast.makeText(MainActivity.this, "显示一下", Toast.LENGTH_SHORT).show();
                new Thread()
                {
                    @Override
                    public void run() {
                        super.run();
                        URL url = null;
                        try {
                           url = new URL("http://www.qiushibaike.com/8hr/page/5");
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                        try {
                            org.jsoup.nodes.Document document = Jsoup.parse(url,3000);
                            Elements elements = document.select("a.contentHerf");
                            Log.v("MainActivity","这儿？");
                            for (int i=0;i<elements.size();i++){
                                Element element = elements.get(i);
                                String href = element.attr("href");
                                Log.e("链接","qqq");
                                Log.e("链接",href);
                                URL urlUser = new URL(url + href);
                                org.jsoup.nodes.Document documentUser = Jsoup.parse(urlUser,3000);
                                Log.e("User", urlUser.toString());
                                Log.e("User", documentUser.toString());
                                Elements elementUsers = documentUser.select("title");
                                Log.e("Title", elementUsers.toString());
                                Elements elementsUserContent = documentUser.select(".contentHerf");
                                Log.e("Content", elementsUserContent.toString());
//                                textView.setText(elementsUserContent.toString());
                            }
                            Log.e("MainActivity", elements.toString());
                            Log.e("MainActivity", document.toString());
                        } catch (IOException e) {
                            Toast.makeText(MainActivity.this, "超时", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    }
                }.start();
//                run();
                Log.e("MainActivity","未执行!");
//                new Thread()
//                {
//                    public void run()
//                    {
//                        try {
//                            org.jsoup.nodes.Document document = Jsoup.connect("http://www.quishibaike.com/").get();
//                            Log.e("MainActivity",document.toString());
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }.start();

            }

        });
    }

//    @Override
//    public void run() {
//        org.jsoup.nodes.Document document = null;
//        try {
//            document = Jsoup.connect("http://www.qiushibaike.com/8hr/page/1/").get();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Log.e("MainActivitu", document.toString());
//    }
}
