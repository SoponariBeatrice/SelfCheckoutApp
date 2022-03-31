package com.example.selfcheckout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class UserAdapter extends ArrayAdapter<User> {

    private Context myContext;
    private int myResource;

    public UserAdapter(@NonNull Context context, int resource, @NonNull List<User> objects) {
        super(context, resource, objects);
        this.myContext = context;
        this.myResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(myContext);
        convertView = layoutInflater.inflate(myResource, parent, false);
        TextView txtName = convertView.findViewById(R.id.username);
        TextView txtEmail = convertView.findViewById(R.id.email);
        txtName.setText(getItem(position).toString());
        txtEmail.setText(getItem(position).getEmail());
        return convertView;
    }

}