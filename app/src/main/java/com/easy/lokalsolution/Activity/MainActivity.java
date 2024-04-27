package com.easy.lokalsolution.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.easy.lokalsolution.Adapter.AdapterViewPager;
import com.easy.lokalsolution.Fragment.FirstFragment;
import com.easy.lokalsolution.R;
import com.easy.lokalsolution.Fragment.SecondFragment;
import com.easy.lokalsolution.Fragment.ThirdFragment;
import com.easy.lokalsolution.Class.UserClass;
import com.easy.lokalsolution.databinding.ActivityMainBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    FirebaseAuth auth;
    GoogleSignInClient mGoogleSignInClient;

    GoogleSignInClient googleSignInClient;
    ProgressDialog dialog;
    ImageView image11;

    Uri selectedImage;
    String name, number, gmail, pic;


    ArrayList<Fragment> fragmentarrylist = new ArrayList<>();
    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth = FirebaseAuth.getInstance();

        dialog = new ProgressDialog(MainActivity.this);
        dialog.setCancelable(false);
        dialog.setMessage("Please wait .....");


        fragmentarrylist.add(new FirstFragment());
        fragmentarrylist.add(new SecondFragment());
        fragmentarrylist.add(new ThirdFragment());


        bottomNavigationView = findViewById(R.id.bottomnav);


        AdapterViewPager adapterViewPager = new AdapterViewPager(MainActivity.this, fragmentarrylist);
        binding.pagermain.setAdapter(adapterViewPager);
        binding.pagermain.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        bottomNavigationView.setSelectedItemId(R.id.news);
                        break;
                    case 1:
                        bottomNavigationView.setSelectedItemId(R.id.query);
                        break;
                    case 2:
                        bottomNavigationView.setSelectedItemId(R.id.ids);
                        break;

                }
                super.onPageSelected(position);
            }
        });

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.news) {
                    binding.pagermain.setCurrentItem(0);
                } else if (itemId == R.id.query) {
                    binding.pagermain.setCurrentItem(1);

                } else if (itemId == R.id.ids) {
                    binding.pagermain.setCurrentItem(2);

                }


                return true;
            }
        });


        binding.progressBar.setVisibility(View.VISIBLE);

        FirebaseFirestore.getInstance().collection("AllUserG").document(FirebaseAuth.getInstance().getUid())
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        DocumentSnapshot snapshot = task.getResult();

                        if (snapshot.exists()) {
                            binding.progressBar.setVisibility(View.GONE);
                            binding.allogoogleprofile.setVisibility(View.VISIBLE);

                            UserClass data = snapshot.toObject(UserClass.class);
                            name = data.getName();
                            number = data.getNumber();
                            gmail = data.getEmail();
                            pic = data.getImage();
                            binding.profilename.setText(name);
                            binding.profileemail.setText(gmail);
                            Picasso.get().load(pic).placeholder(R.drawable.qqqa).into(binding.profilepic);


                        } else {
                            binding.progressBar.setVisibility(View.GONE);
                            binding.allogoogleprofile.setVisibility(View.GONE);
                        }
                    }
                });


        binding.editbtninprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewGroup viewGroup = findViewById(android.R.id.content);

                TextView dname, dnumber;
                Button dadd;

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.updateuserdatadialog, viewGroup, false);
                builder.setCancelable(true);
                builder.setView(view);

                dname = view.findViewById(R.id.dialogeditusername);
                dnumber = view.findViewById(R.id.dialogeditusernumber);
                image11 = view.findViewById(R.id.dialogedituserpic);
                dadd = view.findViewById(R.id.dialogedituserupdatebtn);


                AlertDialog alertDialog = builder.create();
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                dname.setText(name);
                dnumber.setText(number);
                Picasso.get().load(pic).placeholder(R.drawable.qqqa).into(image11);


                image11.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        intent.setType("image/*");
                        startActivityForResult(intent, 45);
                    }
                });
                dadd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.show();
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        String uid = user.getUid();
                        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("AllUser").child(uid);

                        String username = dname.getText().toString();
                        String usernumber = dnumber.getText().toString();

                        if (selectedImage != null) {
                            Toast.makeText(MainActivity.this, "with image", Toast.LENGTH_SHORT).show();
                            storageReference.putFile(selectedImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            String userpic = uri.toString();

                                            HashMap<String, Object> hashMap = new HashMap<>();
                                            hashMap.put("number", usernumber);
                                            hashMap.put("name", username);
                                            hashMap.put("image", userpic);

                                            FirebaseFirestore.getInstance().collection("AllUserG").document(FirebaseAuth.getInstance().getUid())
                                                    .update(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void unused) {
                                                            dialog.dismiss();
                                                            alertDialog.dismiss();
                                                            Toast.makeText(MainActivity.this, "Data Uploaded Successfully", Toast.LENGTH_SHORT).show();

                                                        }
                                                    });

                                        }
                                    });
                                }
                            });

                        } else if (selectedImage == null) {
                            Toast.makeText(MainActivity.this, "with no image", Toast.LENGTH_SHORT).show();

                            HashMap<String, Object> hashMap = new HashMap<>();
                            hashMap.put("number", usernumber);
                            hashMap.put("name", username);

                            FirebaseFirestore.getInstance().collection("AllUserG").document(FirebaseAuth.getInstance().getUid())
                                    .update(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            dialog.dismiss();
                                            alertDialog.dismiss();
                                            Toast.makeText(MainActivity.this, "Data Uploaded Successfully", Toast.LENGTH_SHORT).show();

                                        }
                                    });
                        } else {
                            Toast.makeText(MainActivity.this, "Something is wrong", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

                alertDialog.show();
            }
        });

        binding.sidebarmainbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.sidebarmain.setVisibility(View.VISIBLE);
                binding.just1.setVisibility(View.VISIBLE);

            }
        });
        binding.just1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.sidebarmain.setVisibility(View.GONE);
                binding.just1.setVisibility(View.GONE);


            }
        });
        binding.contactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, SettingShowActivity.class);
                intent.putExtra("type","contactus");
                startActivity(intent);
            }
        });

        binding.aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, SettingShowActivity.class);
                intent.putExtra("type","aboutus");
                startActivity(intent);
            }
        });binding.privacypolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, SettingShowActivity.class);
                intent.putExtra("type","pp");
                startActivity(intent);
            }
        });binding.termandcondition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, SettingShowActivity.class);
                intent.putExtra("type","tc");
                startActivity(intent);
            }
        });binding.moreapps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/developer?id=ResiEasy-+Buy/Rent/Sell+Residency")));

               /* try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://search?q=pub:ResiEasy-+Buy/Rent/Sell+Residency")));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/developer?id=ResiEasy-+Buy/Rent/Sell+Residency")));
                }*/

            }
        });binding.shareapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent=new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_SUBJECT,"ResiEasy");
                    String applink="https://play.google.com/store/apps/details?id="+getApplicationContext().getPackageName();
                    intent.putExtra(Intent.EXTRA_TEXT,applink);
                    startActivity(Intent.createChooser(intent,"Share ResiEasy Application"));

                    startActivity(intent);
                }catch (Exception e){
                    Toast.makeText(MainActivity.this, "Something is wrong", Toast.LENGTH_SHORT).show();
                }

            }
        });binding.rateus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id=" + getApplicationContext().getPackageName())));
                } catch (android.content.ActivityNotFoundException e) {
                    Toast.makeText(MainActivity.this, "Something is wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
        binding.logoutsidebar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(MainActivity.this);
                builder.setIcon(R.drawable.warna);
                builder.setTitle("LOGOUT");
                builder.setMessage("you are sure, you want to logout your account.");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        GoogleSignInOptions gso = new GoogleSignInOptions.
                                Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).
                                build();

                        GoogleSignInClient googleSignInClient = GoogleSignIn.getClient(MainActivity.this, gso);
                        googleSignInClient.signOut().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                HashMap<String, Object> hashMap = new HashMap<>();
                                hashMap.put("token", "");
                                FirebaseFirestore.getInstance().collection("AllUserG").document(FirebaseAuth.getInstance().getUid())
                                        .update(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    auth.signOut();
                                                    Intent intent = new Intent(MainActivity.this, SigninActivity.class);
                                                    startActivity(intent);
                                                    finishAffinity();
                                                    Toast.makeText(MainActivity.this, "Logout Successfully", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });

                            }
                        });

                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).setNeutralButton("Help", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "for logout, press yes", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();


            }
        });
        binding.uploadhere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, UploadFromHareActivity.class);
                startActivity(intent);
            }
        });
        binding.newsidebar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ShowDataToOwnerActivity.class);
                intent.putExtra("type","11");
                startActivity(intent);
            }
        });
        binding.querysidebar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ShowDataToOwnerActivity.class);
                intent.putExtra("type","12");

                startActivity(intent);
            }
        });
        binding.idsidebar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ShowDataToOwnerActivity.class);
                intent.putExtra("type","13");

                startActivity(intent);
            }
        });

    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        new android.app.AlertDialog.Builder(MainActivity.this)
                .setTitle("Exit")
                .setMessage("Are you sure you want to close application ?")
                .setIcon(R.drawable.warna)
                .setCancelable(true)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        finishAffinity();

                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();

                    }
                }).create().show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 45) {
            image11.setImageURI(data.getData());
            selectedImage = data.getData();
        }


    }
}