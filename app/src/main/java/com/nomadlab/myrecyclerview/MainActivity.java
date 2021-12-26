package com.nomadlab.myrecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.nomadlab.myrecyclerview.adapter.FoodListViewAdapter;
import com.nomadlab.myrecyclerview.model.Food;

import java.util.ArrayList;

/*
*   RecyclerView
*   - 장점
*   - ListView 개선판
*      - ViewHolder 포함
*   - 유연하다
*    - LayoutManager (수평으로 리스트를 만들기 쉽다)
*       - Linear
*       - Grid
*       - StaggeredGrid
*     : RecyclerView LayoutManager 타입
* */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Food> foods = Food.getSampleData();

        // 1. 라이브러리 추가
        // 2. itemView xml 파일 (디자인) 생성
        // 3. Adapter 추가
        // 4. LayoutManager 생성 및 연결

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

//      FoodListViewAdapter adapter = new FoodListViewAdapter(foods, this);

        // 콜백 메서드 만드는 방법 추후 설명
        FoodListViewAdapter adapter = new FoodListViewAdapter(foods, this, (view, position) -> {
            Log.d("TAG", foods.get(position).getThumbnail());
        });

        recyclerView.hasFixedSize();
        recyclerView.setAdapter(adapter);

        // 레이아웃 매니져 생성
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
//        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2 , LinearLayout.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        // divider 생성해보기
        DividerItemDecoration dividerItemDecoration
                = new DividerItemDecoration(this, layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);


    }
}