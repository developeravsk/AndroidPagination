package silptech.developerabhi.androidpagination;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.AsyncDifferConfig;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class ItemAdapter extends PagedListAdapter<Item, ItemAdapter.ItemViewHolder> {
    private Context ctx;

    protected ItemAdapter(Context context) {
        super(DIFF_CALLBACK);
        ctx = context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

View view= LayoutInflater.from(ctx).inflate(R.layout.recyclerview_users,parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Item item=getItem(position);
        if(item!=null){
            holder.textView.setText(item.owner.display_name);
            Glide.with(ctx).load(item.owner.profile_image).into(holder.imageView);
        }else{
            Toast.makeText(ctx, "Item is null", Toast.LENGTH_SHORT).show();
        }

    }
    private static DiffUtil.ItemCallback<Item> DIFF_CALLBACK=new DiffUtil.ItemCallback<Item>() {
        @Override
        public boolean areItemsTheSame(Item oldItem, Item newItem) {
            return oldItem.question_id==newItem.question_id;
        }

        @Override
        public boolean areContentsTheSame(Item oldItem, Item newItem) {
            return oldItem.equals(newItem);
        }
    };
    class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        ImageView imageView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textViewName);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
