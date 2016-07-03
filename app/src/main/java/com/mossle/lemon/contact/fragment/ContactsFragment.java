package com.mossle.lemon.contact.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mossle.lemon.R;
import com.mossle.lemon.contact.domain.Contact;
import com.mossle.lemon.contact.service.ContactService;

import java.util.List;

/**
 * Created by LucasLee on 16/7/3.
 */
public class ContactsFragment extends Fragment {

    private RecyclerView mContactsRecyclerView;

    private ContactsAdapter mContactsAdapter;

    public static ContactsFragment newInstance() {
        
        Bundle args = new Bundle();

        ContactsFragment fragment = new ContactsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);
        mContactsRecyclerView = (RecyclerView) view.findViewById(R.id.contactsRecyclerView);
        mContactsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    private void updateUI() {
        List<Contact> contacts = ContactService.sharedInstance().getContacts();
        if (mContactsAdapter == null) {
            mContactsAdapter = new ContactsAdapter(contacts);
            mContactsRecyclerView.setAdapter(mContactsAdapter);
        } else {
            mContactsAdapter.setContacts(contacts);
            mContactsAdapter.notifyDataSetChanged();
        }
    }

    private class ContactViewHolder extends RecyclerView.ViewHolder {

        private Contact contact;

        private ImageView mAvatarImageView;

        private TextView mNameTextView;

        public ContactViewHolder(View itemView) {
            super(itemView);
            mAvatarImageView = (ImageView) itemView.findViewById(R.id.avatarImageView);
            mNameTextView = (TextView) itemView.findViewById(R.id.nameTextView);
        }

        public void bindContact(Contact contact) {
            this.contact = contact;
            mNameTextView.setText(contact.getName());
        }
    }

    private class ContactsAdapter extends RecyclerView.Adapter<ContactViewHolder> {

        private List<Contact> mContacts;

        public ContactsAdapter(List<Contact> contacts) {
            mContacts = contacts;
        }

        @Override
        public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.item_contact, parent, false);
            return new ContactViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ContactViewHolder holder, int position) {
            holder.bindContact(mContacts.get(position));
        }

        @Override
        public int getItemCount() {
            return mContacts.size();
        }

        public void setContacts(List<Contact> contacts) {
            mContacts = contacts;
        }
    }
}
