package com.itheima.rxandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    String[] arr = {"afdsa", "bfdsa", "cfda"};
    @Bind(R.id.imageView)
    ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Course yuwen = new Course(1, "语文");
        Course shuxue = new Course(2, "数学");
        Course yingyu = new Course(3, "英语");
        Course lishi = new Course(4, "历史");
        ArrayList<Course> course1 = new ArrayList<>();
        course1.add(yuwen);
        course1.add(shuxue);
        course1.add(yingyu);
        course1.add(lishi);

        Student zhangsan = new Student("zhangsan", course1);
        Observable
                .just(zhangsan)
                //进行一对多过滤
                .flatMap(new Func1<Student, Observable<Course>>() {
                    @Override
                    public Observable<Course> call(Student student) {
                        return Observable.from(student.getCourses());
                    }
                })
                .subscribe(new Action1<Course>() {
                    @Override
                    public void call(Course course) {
                        Log.e(TAG, "call: from subscribe" + course.getName());
                    }
                });

    }

    private void case04() {
        Observable
                .from(arr)
                .filter(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String s) {
                        return !s.startsWith("a");
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.e(TAG, "call: from subscribe" + s);
                    }
                });
    }

    private void case05() {
        Observable
                .from(arr)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.e(TAG, "call: from subscribe" + s);
                    }
                });
    }

    private void case03() {
        Observable
                .just("Hello")
                .map(new Func1<String, Integer>() {
                    @Override
                    public Integer call(String s) {
                        return s.hashCode();
                    }
                })
                //将hash值转换回String
                .map(new Func1<Integer, String>() {
                    @Override
                    public String call(Integer integer) {
                        return "我被转换成字符串了" + Integer.toString(integer);
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.e(TAG, "call: from subscribe " + s);
                    }
                });
    }

    private void case02() {
        Observable
                .just("Hello")
                //转换为另一个时间, string-->hash值
                .map(new Func1<String, Integer>() {
                    @Override
                    public Integer call(String s) {
                        return s.hashCode();
                    }
                })
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer s) {
                        Log.e(TAG, "call: from subscribe" + s);
                    }
                });
    }

    private void case01() {
        Observable
                .just("Hello")  //发布事件
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        //订阅者处理事件,打印字符串
                        Log.e(TAG, "call: form" + s);
                    }
                });
    }
}
