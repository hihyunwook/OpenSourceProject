package com.dproject.where;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class GroupContactAdapter extends RecyclerView.Adapter<GroupContactAdapter.ContactViewHolder> {
    Context mContext;
    List<Contact> contactList;


    public GroupContactAdapter(Context mContext, List<Contact> contactList) {
        this.mContext = mContext;
        this.contactList = contactList;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.group_item_contact, parent, false);

        ContactViewHolder holder = new ContactViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, final int position) {

        Contact contact = contactList.get(position);
        holder.name_contact.setText(contact.getName());
        holder.phone_contact.setText(contact.getPhone());

        holder.chkSelected.setChecked(contactList.get(position).isSelected());

        holder.chkSelected.setTag(contactList.get(position));

        holder.chkSelected.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CheckBox cb = (CheckBox) v;
                Contact contact = (Contact) cb.getTag();

                contact.setSelected(cb.isChecked());
                contactList.get(position).setSelected(cb.isChecked());

                /*Toast.makeText(
                        v.getContext(),
                        "Clicked on Checkbox: " + cb.getText() + " is "
                                + cb.isChecked(), Toast.LENGTH_LONG).show();*/
            }
        });


        if (contact.getPhoto() != null) {
            Picasso.get().load(contact.getPhoto()).into(holder.img_contact);
        } else {
            holder.img_contact.setImageResource(R.drawable.icon_user);
        }
    }


    @Override
    public int getItemCount() {
        //return 0;
        return contactList.size();
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder {
        TextView name_contact, phone_contact;
        CircleImageView img_contact;
        public CheckBox chkSelected;


        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            name_contact = itemView.findViewById(R.id.name_contact);
            phone_contact = itemView.findViewById(R.id.phone_contact);
            img_contact = itemView.findViewById(R.id.img_contact);
            chkSelected = (CheckBox) itemView.findViewById(R.id.chkSelected);
        }
    }

}
