package com.example.practice.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.practice.BR;
import com.example.practice.R;
import com.example.practice.bean.BorrowBean;
import com.example.practice.databinding.ActivityMvvmBinding;
import com.example.practice.viewmodel.BorrowVM;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

public class MvvmActivity extends AppCompatActivity implements IView{
    private XRecyclerView xRecyclerView;
    private BorrowAdapter adapter;
    /**
     * 这个ActivityMvvmBinding 是如何生成的呢？
     * 他是继承自ViewDataBinding，这个类的生成是有规则的，它是根据对应的布局文件的名字生成的，
     * 比如：activity_main-->ActivityMainBinding 、fragment-->FragmentBinding
     * 即：第一个单词首字母大写，第二个单词首字母大写，最后都会拼上Binding就是生成的Binding类。
     */
    private ActivityMvvmBinding binding;
    private BorrowVM borrowVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_mvvm);
        binding.xRecyclerView.setRefreshProgressStyle(ProgressStyle.BallClipRotate);
        binding.xRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotate);
        binding.xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                borrowVM.loadRefreshData();
            }

            @Override
            public void onLoadMore() {
                borrowVM.loadMoreData();
            }
        });
        //以下两句很重要，不添加的话列表显示不出来，我被这个坑了一上午
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.xRecyclerView.setLayoutManager(layoutManager);
        adapter = new BorrowAdapter(MvvmActivity.this);
        binding.xRecyclerView.setAdapter(adapter);
//        adapter.setBorrowBeanArrayList(new ArrayList<BorrowBean>());
        borrowVM = new BorrowVM(this,adapter);
    }
    public class BorrowAdapter extends RecyclerView.Adapter<BorrowViewHolder>{
        private Context context;
        private ArrayList<BorrowBean> borrowBeanArrayList;
        public BorrowAdapter(Context context){
            this.context = context;
            this.borrowBeanArrayList = new ArrayList<>();
        }

        public void setBorrowBeanArrayList(ArrayList<BorrowBean> borrowBeanArrayList) {
           this.borrowBeanArrayList.addAll(borrowBeanArrayList);
            Toast.makeText(MvvmActivity.this,"size: "+getItemCount(),Toast.LENGTH_LONG).show();
            notifyDataSetChanged();
        }

        @Override
        public void onBindViewHolder(BorrowViewHolder holder, int position) {
            Log.i("wanlijun","position="+position);
            ViewDataBinding dataBinding = holder.getBinding();
            BorrowBean BorrowBean = borrowBeanArrayList.get(position);
            dataBinding.setVariable(BR.borrow, BorrowBean);
            dataBinding.setVariable(BR.adapter,this);
            dataBinding.setVariable(BR.position,position);
            dataBinding.executePendingBindings(); //防止闪烁
        }

        @Override
        public BorrowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_layout,parent,false);
            return new BorrowViewHolder(binding);
        }

        @Override
        public int getItemCount() {
            if(borrowBeanArrayList != null) {
                return borrowBeanArrayList.size();
            }else{
                return 0;
            }
        }
    }
    class BorrowViewHolder extends RecyclerView.ViewHolder{
        private ViewDataBinding binding;
        public BorrowViewHolder(ViewDataBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }

        public ViewDataBinding getBinding() {
            return binding;
        }
    }


    @Override
    public void loadStart(int loadType) {
        Log.i("wanlijun","loadStart");
    }

    @Override
    public void loadFailure(String message) {
        Log.i("wanlijun","loadFailure");
    }

    @Override
    public void loadComplete() {
        Log.i("wanlijun","loadComplete");
    }
}
