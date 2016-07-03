package com.mossle.lemon.message.fragment;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
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

import com.mossle.lemon.R;
import com.mossle.lemon.message.domain.Session;
import com.mossle.lemon.message.service.SessionService;

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

class DividerItemDecoration extends RecyclerView.ItemDecoration {

    private static final int[] ATTRS = new int[] {
            android.R.attr.listDivider
    };

    public static final int HORIZONTAL_LIST = LinearLayout.HORIZONTAL;

    public static final int VERTICAL_LIST = LinearLayout.VERTICAL;

    private Drawable mDivider;

    private int mOrientation;

    public DividerItemDecoration(Context context, int orientation) {
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
        a.recycle();
        setOrientation(orientation);
    }

    public void setOrientation(int orientation) {
        if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST) {
            throw new IllegalArgumentException("invalid orientation");
        }
        mOrientation = orientation;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (mOrientation == VERTICAL_LIST) {
            onDrawVertical(c, parent, state);
        } else {
            onDrawHorizontal(c, parent, state);
        }
    }

    public void onDrawHorizontal(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        final int top = parent.getPaddingTop();
        final int bottom = parent.getHeight() - parent.getPaddingBottom();
        final int childCount = parent.getChildCount();
        for (int index = 0; index < childCount; index++) {
            final View child = parent.getChildAt(index);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int left = child.getRight() + params.rightMargin;
            final int right = left + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(canvas);
        }
    }

    public void onDrawVertical(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();

        final int childCount = parent.getChildCount();
        for (int index = 0; index < childCount; index++) {
            final View child = parent.getChildAt(index);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(canvas);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (mOrientation == VERTICAL_LIST) {
            outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
        } else {
            outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
        }
    }
}