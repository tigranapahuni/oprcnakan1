package com.narsli.buttomsheet2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

/*
https://codinginflow.com/tutorials/android/persistent-bottom-sheet
создать постоянный нижний лист, который мы можем перетаскивать вверх и вниз,
чтобы показать и скрыть его содержимое. Для листа мы будем использовать
NestedScrollView и установить для его layout_behavior значение «BottomSheetBehavior», чтобы сообщить системе, что мы хотим использовать этот NestedScrollView в качестве нижнего листа. Мы также определим peekHeight и установим для hideable значение true, чтобы полностью убрать его с экрана.
В коде Java мы также свернем и развернем его программно (по нажатию кнопки),
вызвав setState для нашего BottomSheetBehavior и передав ему константу состояния.
Также мы будем прослушивать изменения состояния на нашем нижнем листе,
устанавливая для него функцию BottomSheetCallback и реагируя на STATE_COLLAPSED,
STATE_DRAGGING, STATE_EXPANDED, STATE_HIDDEN, STATE_SETTLING и onSlide
 */
public class MainActivity extends AppCompatActivity {
    private BottomSheetBehavior mBottomSheetBehavior;
    private TextView mTextViewState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View bottomSheet = findViewById(R.id.bottom_sheet);

        mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);

        mTextViewState = findViewById(R.id.text_view_state);

        Button buttonExpand = findViewById(R.id.button_expand);
        Button buttonCollapse = findViewById(R.id.button_collapse);

        buttonExpand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });

        buttonCollapse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });

        mBottomSheetBehavior.setBottomSheetCallback(new
                BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        mTextViewState.setText("Collapsed");
                        break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        mTextViewState.setText("Dragging...");
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        mTextViewState.setText("Expanded");
                        break;
                    case BottomSheetBehavior.STATE_HIDDEN:
                        mTextViewState.setText("Hidden");
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        mTextViewState.setText("Settling...");
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                mTextViewState.setText("Sliding...");
            }
        });
    }
}