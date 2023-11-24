package com.example.trabalho_tsi2.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.trabalho_tsi2.InitialActivity;
import com.example.trabalho_tsi2.R;
import com.example.trabalho_tsi2.database.DatabaseSingleton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class ProfileFragment extends Fragment {
    private final FirebaseAuth auth = FirebaseAuth.getInstance();
    private final DatabaseReference profiles = DatabaseSingleton.getInstance().profiles;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        TextView fullnameText = view.findViewById(R.id.fullnameText);
        TextView rgaText = view.findViewById(R.id.rgaText);
        Button signoutButton = view.findViewById(R.id.signoutButton);

        String uid = auth.getUid();
        assert uid != null;
        DatabaseReference profileReference = profiles.child(uid);

        profileReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    Profile profile = snapshot.getValue(Profile.class);
                    assert profile != null;
                    fullnameText.setText(profile.getFullName());
                    rgaText.setText(profile.getRga());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        signoutButton.setOnClickListener(buttonView -> {
            signoutButton.setEnabled(false);
            auth.signOut();
            Intent intent = new Intent(getContext(), InitialActivity.class);
            startActivity(intent);
            this.getActivity().finish();
        });

        return view;
    }
}
