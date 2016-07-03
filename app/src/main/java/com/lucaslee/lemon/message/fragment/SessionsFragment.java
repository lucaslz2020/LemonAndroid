package com.lucaslee.lemon.message.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lucaslee.lemon.R;
import com.lucaslee.lemon.message.domain.Session;
import com.lucaslee.lemon.message.service.SessionService;
import com.lucaslee.lemon.widget.DividerItemDecoration;

import java.util.List;

/**
 * Created by LucasLee on 16/7/3.
 */
public class SessionsFragment extends Fragment {

    public static SessionsFragment newInstance() {

        Bundle args = new Bundle();

        SessionsFragment fragment = new SessionsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private RecyclerView mSessionsRecyclerView;

    private SessionsAdapter mSessionAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sessions, container, false);
        mSessionsRecyclerView = (RecyclerView) view.findViewById(R.id.sessionsRecyclerView);
        mSessionsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mSessionsRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayout.VERTICAL));

        updateUI();

        return view;
    }

    private void updateUI() {
        List<Session> sessions = SessionService.sharedInstance().getSessions();
        if (mSessionAdapter == null) {
            mSessionAdapter = new SessionsAdapter(sessions);
            mSessionsRecyclerView.setAdapter(mSessionAdapter);
        } else {
            mSessionAdapter.setSessions(sessions);
            mSessionAdapter.notifyDataSetChanged();
        }
    }

    private class SessionViewHolder extends RecyclerView.ViewHolder {

        private Session session;

        private ImageView avatarImageView;

        private TextView nameTextView;

        private TextView contentTextView;

        private TextView timeTextView;

        public SessionViewHolder(View itemView) {
            super(itemView);
            avatarImageView = (ImageView) itemView.findViewById(R.id.avatarImageView);
            nameTextView = (TextView) itemView.findViewById(R.id.nameTextView);
            contentTextView = (TextView) itemView.findViewById(R.id.contentTextView);
            timeTextView = (TextView) itemView.findViewById(R.id.timeTextView);
        }

        public void bindSession(Session session) {
            this.session = session;
            nameTextView.setText(session.getName());
            contentTextView.setText(session.getContent());
            timeTextView.setText(session.getCreatedTime().toLocaleString());
        }
    }

    private class SessionsAdapter extends RecyclerView.Adapter<SessionViewHolder> {

        private List<Session> sessions;

        public SessionsAdapter(List<Session> sessions) {
            this.sessions = sessions;
        }

        @Override
        public SessionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.item_session, parent, false);
            return new SessionViewHolder(view);
        }

        @Override
        public void onBindViewHolder(SessionViewHolder holder, int position) {
            holder.bindSession(sessions.get(position));
        }

        @Override
        public int getItemCount() {
            return sessions.size();
        }

        public List<Session> getSessions() {
            return sessions;
        }

        public void setSessions(List<Session> sessions) {
            this.sessions = sessions;
        }
    }
}