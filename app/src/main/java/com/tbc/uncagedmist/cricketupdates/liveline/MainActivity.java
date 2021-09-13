package com.tbc.uncagedmist.cricketupdates.liveline;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.NetworkImageView;
import com.tbc.uncagedmist.cricketupdates.R;
import com.tbc.uncagedmist.cricketupdates.Tools.AppAdOrganizer;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdBase;
import com.facebook.ads.NativeAdLayout;
import com.facebook.ads.NativeAdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAdView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
//import com.cricket.live.line.R;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import static com.tbc.uncagedmist.cricketupdates.Tools.AppTimeHandler.appStructureBase;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private RequestQueue requestQueue;
    private RequestQueue requestQueue2;
    private RequestQueue requestQueue3;

    int f18550aX = 0;
    FirebaseDatabase database;
    Vibrator f18532aE;
    ScrollView scrollvirewe;

    boolean f18601n = true;
    boolean f18588bi = false;

    static String f18495aS = "";

    /* renamed from: bq */
    static String f18496bq = "";

    /* renamed from: br */
    static String f18497br = "";

    /* renamed from: bs */
    static String f18498bs = "";

    /* renamed from: bt */
    static String f18499bt = "";

    /* renamed from: bu */
    static String f18500bu = "";

    /* renamed from: bv */
    static String f18501bv = "";

    /* renamed from: A */
    TextView f18502A;

    /* renamed from: B */
    TextView f18503B;

    /* renamed from: C */
    TextView f18504C;

    /* renamed from: D */
    TextView f18505D;

    /* renamed from: E */
    TextView f18506E;

    /* renamed from: F */
    TextView f18507F;

    /* renamed from: G */
    TextView f18508G;

    /* renamed from: H */
    TextView f18509H;

    /* renamed from: I */
    TextView f18510I;

    /* renamed from: J */
    TextView f18511J;

    /* renamed from: K */
    TextView f18512K;

    /* renamed from: L */
    TextView f18513L;

    /* renamed from: M */
    TextView f18514M;

    /* renamed from: N */
    TextView f18515N;

    /* renamed from: O */
    TextView f18516O;

    /* renamed from: P */
    TextView f18517P;
    /* renamed from: Q */
    TextView f18518Q;
    /* renamed from: R */
    TextView f18519R;
    /* renamed from: S */
    TextView f18520S;
    /* renamed from: T */
    TextView f18521T;
    /* renamed from: U */
    TextView f18522U;
    /* renamed from: V */
    TextView f18523V;
    /* renamed from: W */
    TextView f18524W;
    /* renamed from: X */
    TextView f18525X;
    /* renamed from: Y */
    TextView f18526Y;
    /* renamed from: Z */
    TextView f18527Z;
    /* renamed from: aA */
    long f18528aA;

    /* renamed from: aB */
    long f18529aB;

    /* renamed from: aC */
    long f18530aC;

    /* renamed from: aD */
    /* renamed from: aE */
    /* renamed from: aF */
    AlphaAnimation f18533aF;

    /* renamed from: aG */
    TextView f18534aG;

    /* renamed from: aH */
    TextView f18535aH;

    /* renamed from: aI */
    TextView f18536aI;

    /* renamed from: aJ */
    TextView f18537aJ;

    /* renamed from: aK */
    TextView f18538aK;

    /* renamed from: aL */
    TextView f18539aL;

    /* renamed from: aM */
    TextView f18540aM;

    /* renamed from: aN */
    TextView f18541aN;

    /* renamed from: aO */
    TextView f18542aO;

    /* renamed from: aP */
    Animation f18543aP;

    /* renamed from: aQ */
    Animation f18544aQ;

    /* renamed from: aR */
    RelativeLayout f18545aR;

    /* renamed from: aT */
    String f18546aT;

    /* renamed from: aU */
    String f18547aU;
    /* renamed from: aV */
    ImageView f18548aV;
    /* renamed from: aW */
    ProgressBar f18549aW;
    /* renamed from: aX */

    /* renamed from: aY */
    double f18551aY;

    /* renamed from: aZ */
    double f18552aZ;

    /* renamed from: aa */
    TextView f18553aa;

    /* renamed from: ab */
    TextView f18554ab;

    /* renamed from: ac */
    TextView f18555ac;

    /* renamed from: ad */
    TextView f18556ad;

    /* renamed from: ae */
    TextView f18557ae;

    /* renamed from: af */
    TextView f18558af;

    /* renamed from: ag */
    TextView f18559ag;

    /* renamed from: ah */
    TextView f18560ah;

    /* renamed from: ai */
    TextView f18561ai;

    /* renamed from: aj */
    TextView f18562aj;

    /* renamed from: ak */
    TextView f18563ak;

    /* renamed from: al */
    TextView f18564al;

    /* renamed from: am */
    TextView f18565am;

    /* renamed from: an */
    TextView f18566an;

    /* renamed from: ao */
    String f18567ao = "";

    /* renamed from: ap */
    String f18568ap = "";

    /* renamed from: aq */
    long f18569aq;

    /* renamed from: ar */
    long f18570ar;

    /* renamed from: as */
    long f18571as;

    /* renamed from: at */
    long f18572at;

    /* renamed from: au */
    long f18573au;

    /* renamed from: av */
    long f18574av;

    /* renamed from: aw */
    long f18575aw;

    /* renamed from: ax */
    long f18576ax;

    /* renamed from: ay */
    long f18577ay;

    /* renamed from: az */
    long f18578az;

    double f18580ba;

    /* renamed from: bb */
    double f18581bb;

    /* renamed from: bc */
    TextView f18582bc;

    /* renamed from: bd */
    TextView f18583bd;

    /* renamed from: be */
    TextView f18584be;

    /* renamed from: bf */
    TextView f18585bf;

    /* renamed from: bg */
    TextView f18586bg;

    /* renamed from: bh */
    TextView f18587bh;

    /* renamed from: bi */

    /* renamed from: bj */
    RelativeLayout f18589bj;

    /* renamed from: bk */
    TextView f18590bk;

    /* renamed from: bl */
    TextView f18591bl;

    /* renamed from: bm */
    TextView f18592bm;

    /* renamed from: bn */
    NetworkImageView f18593bn;

    /* renamed from: bo */
    ImageView f18594bo;

    /* renamed from: bp */
    TextView f18595bp;
    /* access modifiers changed from: private */

    /* renamed from: bw */

    /* renamed from: bx */
    private Timer f18597bx;

    /* renamed from: by */
    private TimerTask f18598by;

    /* renamed from: bz */

    /* renamed from: m */

    /* renamed from: n */

    /* renamed from: o */
    TextToSpeech f18602o;

    /* renamed from: p */
    TextView f18603p;

    /* renamed from: q */
    TextView f18604q;

    /* renamed from: r */
    TextView f18605r;

    /* renamed from: s */
    TextView f18606s;

    /* renamed from: t */
    TextView f18607t;

    /* renamed from: u */
    TextView f18608u;

    /* renamed from: v */
    TextView f18609v;

    /* renamed from: w */
    TextView f18610w;

    /* renamed from: x */
    TextView f18611x;

    /* renamed from: y */
    TextView f18612y;

    /* renamed from: z */
    TextView f18613z;
ImageView back;

    ProgressBar newsloaderjsoup;
    FrameLayout googleadViewNative;
    private NativeAdLayout nativeAdLayout;
    private UnifiedNativeAd nativeAd;
    private NativeAd fbnativeAd;
    @Override
    public void onBackPressed() {
        finish();
    }

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.match_detail_new);
//        getData();
//        fetch_live_matches();
//        getLiveMatch();
        back =findViewById(R.id.back);
back.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        onBackPressed();
    }
});



        googleadViewNative = findViewById(R.id.gooogleadViewNative);
        nativeAdLayout = findViewById(R.id.fbnative_ad_container);
        if (appStructureBase != null && appStructureBase.getFb_native_liveline() == 1) {

            fbnativeAd = new NativeAd(this, appStructureBase.getFb_native_id());
            NativeAdBase.NativeLoadAdConfig nativeLoadAdConfig = fbnativeAd
                    .buildLoadAdConfig()
                    .withAdListener(new NativeAdListener() {
                        @SuppressLint("LongLogTag")
                        @Override
                        public void onMediaDownloaded(Ad ad) {
                        }

                        @Override
                        public void onError(Ad ad, AdError adError) {
                        }

                        @Override
                        public void onAdLoaded(Ad ad) {

                            try {

                                if (fbnativeAd == null || fbnativeAd != ad) {
                                    return;
                                }
                                AppAdOrganizer.getInstance().inflateAd(MainActivity.this, fbnativeAd, nativeAdLayout);
                                nativeAdLayout.setVisibility(View.VISIBLE);
                            } catch (Exception ignored) {

                            }
                        }

                        @Override
                        public void onAdClicked(Ad ad) {
                        }

                        @Override
                        public void onLoggingImpression(Ad ad) {
                        }
                    }).build();

            fbnativeAd.loadAd(nativeLoadAdConfig);

        }
        else if (appStructureBase != null && appStructureBase.getGoogle_native_liveline() == 1) {

            AdLoader adLoader = new AdLoader.Builder(Objects.requireNonNull(this), appStructureBase.getNative_id())
                    .forUnifiedNativeAd(unifiedNativeAd -> {
                        googleadViewNative.setVisibility(View.VISIBLE);
                        try {
                            if (nativeAd != null) {
                                nativeAd.destroy();
                            }
                            nativeAd = unifiedNativeAd;
                            @SuppressLint("InflateParams")
                            UnifiedNativeAdView adView = (UnifiedNativeAdView) LayoutInflater.from(this).inflate(R.layout.ad_unified, null);
                            AppAdOrganizer.getInstance().loadAdMobNativeAd(unifiedNativeAd, adView);
                            googleadViewNative.removeAllViews();
                            googleadViewNative.addView(adView);
                        } catch (Exception ignored) {

                        }
                    }).build();
            adLoader.loadAd(new AdRequest.Builder().build());

        } else {
            nativeAdLayout.setVisibility(View.GONE);
            googleadViewNative.setVisibility(View.GONE);
        }




        this.f18582bc = (TextView) findViewById(R.id.thisover1);
        this.f18583bd = (TextView) findViewById(R.id.thisover2);
        this.f18584be = (TextView) findViewById(R.id.thisover3);
        this.f18585bf = (TextView) findViewById(R.id.thisover4);
        this.f18586bg = (TextView) findViewById(R.id.thisover5);
        this.f18587bh = (TextView) findViewById(R.id.thisover6);
        this.scrollvirewe = (ScrollView) findViewById(R.id.scrollvirewe);
        this.f18549aW = (ProgressBar) findViewById(R.id.progressBar);
        this.f18545aR = (RelativeLayout) findViewById(R.id.targetLayout);
        this.f18522U = (TextView) findViewById(R.id.last12balls);
        this.f18520S = (TextView) findViewById(R.id.player1Ball);
        this.f18521T = (TextView) findViewById(R.id.player2Ball);
        this.f18539aL = (TextView) findViewById(R.id.player1Run);
        this.f18540aM = (TextView) findViewById(R.id.player2Run);
        this.f18541aN = (TextView) findViewById(R.id.bolwerBalls);
        this.f18513L = (TextView) findViewById(R.id.currentlyPlaying);
        this.f18512K = (TextView) findViewById(R.id.bowlerName);
        this.f18534aG = (TextView) findViewById(R.id.messageBoxBlink);
        this.f18535aH = (TextView) findViewById(R.id.marketBlink);
        this.f18511J = (TextView) findViewById(R.id.match_name);
        this.f18603p = (TextView) findViewById(R.id.match_time);
        this.f18604q = (TextView) findViewById(R.id.textruns);
        this.f18510I = (TextView) findViewById(R.id.tvFav);
        this.f18605r = (TextView) findViewById(R.id.textovers);
        this.f18606s = (TextView) findViewById(R.id.texttarget);
        this.f18607t = (TextView) findViewById(R.id.player1);
        this.f18608u = (TextView) findViewById(R.id.player2);
        this.f18609v = (TextView) findViewById(R.id.market_rate1);
        this.f18610w = (TextView) findViewById(R.id.market_rate2);
        this.f18611x = (TextView) findViewById(R.id.market_rate_text);
        this.f18612y = (TextView) findViewById(R.id.session_box1);
        this.f18613z = (TextView) findViewById(R.id.session_box2);
        this.f18502A = (TextView) findViewById(R.id.session_box3);
        this.f18503B = (TextView) findViewById(R.id.session_box4);
        this.f18504C = (TextView) findViewById(R.id.session_box5);
        this.f18505D = (TextView) findViewById(R.id.session_box6);
        this.f18506E = (TextView) findViewById(R.id.session_edit_over);
        this.f18507F = (TextView) findViewById(R.id.session_edit_lambi);
        this.f18508G = (TextView) findViewById(R.id.sessionOver3);
        this.f18509H = (TextView) findViewById(R.id.runs_needed);
        this.f18536aI = (TextView) findViewById(R.id.runs_neededReal);
        this.f18537aJ = (TextView) findViewById(R.id.balls_remaining);
        this.f18538aK = (TextView) findViewById(R.id.rrr);
        this.f18514M = (TextView) findViewById(R.id.sessionminusruns1);
        this.f18515N = (TextView) findViewById(R.id.sessionminusruns2);
        this.f18516O = (TextView) findViewById(R.id.sessionminusruns3);
        this.f18517P = (TextView) findViewById(R.id.runminusoverintosix1);
        this.f18518Q = (TextView) findViewById(R.id.runminusoverintosix2);
        this.f18519R = (TextView) findViewById(R.id.runminusoverintosix3);
        this.f18560ah = (TextView) findViewById(R.id.bowlerNameText);
        this.f18561ai = (TextView) findViewById(R.id.ballingRunsText);
        this.f18562aj = (TextView) findViewById(R.id.bowlingZeroText);
        this.f18563ak = (TextView) findViewById(R.id.ballingMaidenText);
        this.f18564al = (TextView) findViewById(R.id.ballingWicketText);
        this.f18565am = (TextView) findViewById(R.id.ballingEcoText);
        this.f18523V = (TextView) findViewById(R.id.batsmanName1);
        this.f18524W = (TextView) findViewById(R.id.batsmanNameRun1);
        this.f18525X = (TextView) findViewById(R.id.batsmanBall1);
        this.f18526Y = (TextView) findViewById(R.id.batsman4s1);
        this.f18527Z = (TextView) findViewById(R.id.batsman6s1);
        this.f18553aa = (TextView) findViewById(R.id.batsmanSR1);
        this.f18554ab = (TextView) findViewById(R.id.batsmanName2);
        this.f18555ac = (TextView) findViewById(R.id.batsmanNameRun2);
        this.f18556ad = (TextView) findViewById(R.id.batsmanBall2);
        this.f18557ae = (TextView) findViewById(R.id.batsman4s2);
        this.f18558af = (TextView) findViewById(R.id.batsman6s2);
        this.f18559ag = (TextView) findViewById(R.id.batsmanSR2);
        this.f18566an = (TextView) findViewById(R.id.Last12Balls);
        this.f18542aO = (TextView) findViewById(R.id.textcrr);
        this.f18548aV = (ImageView) findViewById(R.id.disablespeecbutton);
        this.f18533aF = new AlphaAnimation(1.0f, 0.0f);
        this.f18533aF.setDuration(500);
        this.f18533aF.setInterpolator(new LinearInterpolator());
        this.f18533aF.setRepeatCount(2);
        this.f18533aF.setRepeatMode(2);
        this.f18595bp = (TextView) findViewById(R.id.fullscorecard);

        final SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        final boolean z = defaultSharedPreferences.getBoolean("speechnew1", false);

        if (z) {
            this.f18548aV.setImageDrawable(getResources().getDrawable(R.drawable.volumenew));
        } else {
            this.f18548aV.setImageDrawable(getResources().getDrawable(R.drawable.mute));
        }

        this.f18548aV.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                if (defaultSharedPreferences.getBoolean("speechnew1", false)) {
                    MainActivity.this.f18548aV.setImageDrawable(MainActivity.this.getResources().getDrawable(R.drawable.mute));
                    defaultSharedPreferences.edit().putBoolean("speechnew1", false).commit();
                } else if (!z) {
                    MainActivity.this.f18548aV.setImageDrawable(MainActivity.this.getResources().getDrawable(R.drawable.volumenew));
                    defaultSharedPreferences.edit().putBoolean("speechnew1", true).commit();
                }
            }
        });

        this.f18532aE = (Vibrator) getSystemService("vibrator");
        this.f18602o = new TextToSpeech(this, this);

//        DatabaseReference ref = database.getReference("server/path/to/profile");
        database = FirebaseDatabase.getInstance();
        database.getReference("12oversNew").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String str = (String) dataSnapshot.getValue(String.class);
                if (str.length() <= 24) {
                    MainActivity.this.f18522U.setText(str);
                    return;
                }
                MainActivity.this.f18522U.setText(str.substring(str.length() - 24));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        database.getReference("matchName").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                MainActivity.this.f18511J.setText((String) dataSnapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("bhaiji", "The read failed:");
            }
        });


        database.getReference("messageBoxInfo").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                MainActivity.this.f18549aW.setVisibility(8);
                MainActivity.this.scrollvirewe.setVisibility(View.VISIBLE);
                String str = (String) dataSnapshot.getValue(String.class);

                if (str.equals("whatsapp")){
//                    str=null;
                    Toast.makeText(MainActivity.this, "Please Re code", Toast.LENGTH_SHORT).show();
                }



                if (str.equals("1") || str.equals("2") || str.equals("3") || str.equals("4-4-4") || str.equals("6-6-6") || str.equals("5") || str.equals("Ball") || str.equals("Wicket")) {
                    MainActivity.this.f18603p.setTextSize(24.0f);
                } else {
                    MainActivity.this.f18603p.setTextSize(18.0f);
                }
                AnimationUtils.loadAnimation(MainActivity.this.getApplicationContext(), R.anim.shake);
                MainActivity.this.f18543aP = AnimationUtils.loadAnimation(MainActivity.this, R.anim.zoom_in);
                MainActivity.this.f18544aQ = AnimationUtils.loadAnimation(MainActivity.this, R.anim.zoom_out);
                MainActivity.this.f18603p.setText(str);
                MainActivity.this.f18603p.startAnimation(MainActivity.this.f18543aP);
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        MainActivity.this.f18603p.startAnimation(MainActivity.this.f18544aQ);
                    }
                }, 200);

                if (MainActivity.this.f18550aX < 2) {
                    MainActivity.this.f18550aX++;
                }
                SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                if (defaultSharedPreferences.getBoolean("vibrate", false)) {
                    MainActivity.this.f18532aE.vibrate(30);
                }
                MediaPlayer mediaPlayer = new MediaPlayer();

                if (MainActivity.this.f18601n) {
                    PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                    boolean z = defaultSharedPreferences.getBoolean("speechnew1", false);
                    if (z) {
                        MainActivity.this.f18588bi = true;
                        try {
                            if (str.equals("Ball")) {
                                mediaPlayer.reset();
                                AssetFileDescriptor openFd = MainActivity.this.getAssets().openFd("ball.mp3");
                                mediaPlayer.setDataSource(openFd.getFileDescriptor(), openFd.getStartOffset(), openFd.getLength());
                                mediaPlayer.prepare();
                                mediaPlayer.start();
                            } else if (str.equals("1")) {
                                mediaPlayer.reset();
                                AssetFileDescriptor openFd2 = MainActivity.this.getAssets().openFd("single.mp3");
                                mediaPlayer.setDataSource(openFd2.getFileDescriptor(), openFd2.getStartOffset(), openFd2.getLength());
                                mediaPlayer.prepare();
                                mediaPlayer.start();
                            } else if (str.equals("2")) {
                                mediaPlayer.reset();
                                AssetFileDescriptor openFd3 = MainActivity.this.getAssets().openFd("double.mp3");
                                mediaPlayer.setDataSource(openFd3.getFileDescriptor(), openFd3.getStartOffset(), openFd3.getLength());
                                mediaPlayer.prepare();
                                mediaPlayer.start();
                            } else if (str.equals("3")) {
                                mediaPlayer.reset();
                                AssetFileDescriptor openFd4 = MainActivity.this.getAssets().openFd("triple.mp3");
                                mediaPlayer.setDataSource(openFd4.getFileDescriptor(), openFd4.getStartOffset(), openFd4.getLength());
                                mediaPlayer.prepare();
                                mediaPlayer.start();
                            } else if (str.equals("4-4-4")) {
                                mediaPlayer.reset();
                                AssetFileDescriptor openFd5 = MainActivity.this.getAssets().openFd("four.mp3");
                                mediaPlayer.setDataSource(openFd5.getFileDescriptor(), openFd5.getStartOffset(), openFd5.getLength());
                                mediaPlayer.prepare();
                                mediaPlayer.start();
                            } else if (str.equals("5")) {
                                mediaPlayer.reset();
                                AssetFileDescriptor openFd6 = MainActivity.this.getAssets().openFd("five.mp3");
                                mediaPlayer.setDataSource(openFd6.getFileDescriptor(), openFd6.getStartOffset(), openFd6.getLength());
                                mediaPlayer.prepare();
                                mediaPlayer.start();
                            } else if (str.equals("6-6-6")) {
                                mediaPlayer.reset();
                                AssetFileDescriptor openFd7 = MainActivity.this.getAssets().openFd("six.mp3");
                                mediaPlayer.setDataSource(openFd7.getFileDescriptor(), openFd7.getStartOffset(), openFd7.getLength());
                                mediaPlayer.prepare();
                                mediaPlayer.start();
                            } else if (str.equals("Wide")) {
                                mediaPlayer.reset();
                                AssetFileDescriptor openFd8 = MainActivity.this.getAssets().openFd("wide.mp3");
                                mediaPlayer.setDataSource(openFd8.getFileDescriptor(), openFd8.getStartOffset(), openFd8.getLength());
                                mediaPlayer.prepare();
                                mediaPlayer.start();
                            } else if (str.equals("Wicket")) {
                                mediaPlayer.reset();
                                AssetFileDescriptor openFd9 = MainActivity.this.getAssets().openFd("wicket.mp3");
                                mediaPlayer.setDataSource(openFd9.getFileDescriptor(), openFd9.getStartOffset(), openFd9.getLength());
                                mediaPlayer.prepare();
                                mediaPlayer.start();
                            } else if (str.equals("No Ball")) {
                                mediaPlayer.reset();
                                AssetFileDescriptor openFd10 = MainActivity.this.getAssets().openFd("noball.mp3");
                                mediaPlayer.setDataSource(openFd10.getFileDescriptor(), openFd10.getStartOffset(), openFd10.getLength());
                                mediaPlayer.prepare();
                                mediaPlayer.start();
                            } else if (str.equals("Ball Hawa Me")) {
                                mediaPlayer.reset();
                                AssetFileDescriptor openFd11 = MainActivity.this.getAssets().openFd("ballhawame.mp3");
                                mediaPlayer.setDataSource(openFd11.getFileDescriptor(), openFd11.getStartOffset(), openFd11.getLength());
                                mediaPlayer.prepare();
                                mediaPlayer.start();
                            } else if (str.equals("0")) {
                                mediaPlayer.reset();
                                AssetFileDescriptor openFd12 = MainActivity.this.getAssets().openFd("zero.mp3");
                                mediaPlayer.setDataSource(openFd12.getFileDescriptor(), openFd12.getStartOffset(), openFd12.getLength());
                                mediaPlayer.prepare();
                                mediaPlayer.start();
                            } else if (str.equals("Third Umpire")) {
                                mediaPlayer.reset();
                                AssetFileDescriptor openFd13 = MainActivity.this.getAssets().openFd("thirdumpire.mp3");
                                mediaPlayer.setDataSource(openFd13.getFileDescriptor(), openFd13.getStartOffset(), openFd13.getLength());
                                mediaPlayer.prepare();
                                mediaPlayer.start();
                            }
                        } catch (IllegalArgumentException e) {
                            e.printStackTrace();
                        } catch (IllegalStateException e2) {
                            e2.printStackTrace();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                        new Handler().postDelayed(new Runnable() {
                            public void run() {
                                MainActivity.this.f18588bi = false;
                            }
                        }, 1500);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("bhaiji", "The read failed:");
            }
        });


        database.getReference("runs").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Long l = (Long) dataSnapshot.getValue(Long.class);
                String valueOf = String.valueOf(l);
                MainActivity.this.f18567ao = valueOf;
                MainActivity.this.f18570ar = l.longValue();
                MainActivity.this.f18604q.setText(valueOf + "/" + MainActivity.this.f18568ap);

                if (MainActivity.this.f18550aX == 2) {
                    MainActivity.this.f18543aP = AnimationUtils.loadAnimation(MainActivity.this, R.anim.zoom_in_big);
                    MainActivity.this.f18544aQ = AnimationUtils.loadAnimation(MainActivity.this, R.anim.zoom_out_big);
                    MainActivity.this.f18604q.startAnimation(MainActivity.this.f18543aP);
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            MainActivity.this.f18604q.startAnimation(MainActivity.this.f18544aQ);
                        }
                    }, 400);
                }

                MainActivity.this.f18516O.setText(String.valueOf(MainActivity.this.f18575aw - MainActivity.this.f18570ar));
                long j = MainActivity.this.f18576ax - MainActivity.this.f18570ar;
                if (j > 0) {
                    MainActivity.this.f18515N.setText(String.valueOf(j));
                } else {
                    MainActivity.this.f18515N.setText("0");
                }
                long j2 = MainActivity.this.f18577ay - MainActivity.this.f18570ar;
                if (j2 > 0) {
                    MainActivity.this.f18514M.setText(String.valueOf(j2));
                } else {
                    MainActivity.this.f18514M.setText("0");
                }
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        long j = MainActivity.this.f18578az - MainActivity.this.f18570ar;
                        double d = (double) (MainActivity.this.f18578az - MainActivity.this.f18570ar);
                        if (j > 0) {
                            MainActivity.this.f18536aI.setText(String.valueOf(j));
                        } else {
                            MainActivity.this.f18536aI.setText("0");
                        }
                        long j2 = MainActivity.this.f18528aA - MainActivity.this.f18574av;
                        double d2 = (double) (MainActivity.this.f18528aA - MainActivity.this.f18574av);
                        if (j2 > 0) {
                            double d3 = (d * 6.0d) / d2;
                            if (d3 > 0.0d) {
                                MainActivity.this.f18538aK.setText(String.valueOf(String.format("%.2f", new Object[]{Double.valueOf(d3)})));
                            } else {
                                MainActivity.this.f18538aK.setText("0");
                            }
                        }
                        if (MainActivity.this.f18578az == 0) {
                            MainActivity.this.f18537aJ.setText("--");
                        } else {
                            MainActivity.this.f18537aJ.setText(String.valueOf(j2));
                        }
                    }
                }, 1000);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("bhaiji", "The read failed:");
            }
        });

        database.getReference("balls").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int intValue = ((Integer) dataSnapshot.getValue(Integer.class)).intValue();
                if (intValue == 18) {
//                    MainActivity.this.f18531aD.mo8319a((C2699a) new C2699a() {
//                        /* renamed from: b */
//                        public void mo6968b() {
//                            if (MainActivity.this.f18531aD.mo8324a()) {
//                                new Handler().postDelayed(new Runnable() {
//                                    public void run() {
//                                        if (MainActivity.this.f18601n) {
//                                            MainActivity.this.f18531aD.mo8325b();
//                                        }
//                                    }
//                                }, 2000);
//                            }
//                        }
//                    });
                } else if (intValue == 36) {
//                    MainActivity.this.f18531aD = new C2732g(MainActivity.this);
//                    MainActivity.this.f18531aD.mo8322a("ca-app-pub-7120982326886980/6266306553");
//                    MainActivity.this.f18531aD.mo8321a(new C2720c.C2722a().mo8273a());
//                    MainActivity.this.f18531aD.mo8319a((C2699a) new C2699a() {
//                        /* renamed from: b */
//                        public void mo6968b() {
//                            if (MainActivity.this.f18531aD.mo8324a()) {
//                                new Handler().postDelayed(new Runnable() {
//                                    public void run() {
//                                        if (MainActivity.this.f18601n) {
//                                            MainActivity.this.f18531aD.mo8325b();
//                                        }
//                                    }
//                                }, 2000);
//                            }
//                        }
//                    });
                } else if (intValue == 60) {
//                    MainActivity.this.f18531aD = new C2732g(MainActivity.this);
//                    MainActivity.this.f18531aD.mo8322a("ca-app-pub-7120982326886980/6266306553");
//                    MainActivity.this.f18531aD.mo8321a(new C2720c.C2722a().mo8273a());
//                    MainActivity.this.f18531aD.mo8319a((C2699a) new C2699a() {
//                        /* renamed from: b */
//                        public void mo6968b() {
//                            if (MainActivity.this.f18531aD.mo8324a()) {
//                                new Handler().postDelayed(new Runnable() {
//                                    public void run() {
//                                        if (MainActivity.this.f18601n) {
//                                            MainActivity.this.f18531aD.mo8325b();
//                                        }
//                                    }
//                                }, 2000);
//                            }
//                        }
//                    });
                } else if (intValue == 90) {
//                    MainActivity.this.f18531aD = new C2732g(MainActivity.this);
//                    MainActivity.this.f18531aD.mo8322a("ca-app-pub-7120982326886980/6266306553");
//                    MainActivity.this.f18531aD.mo8321a(new C2720c.C2722a().mo8273a());
//                    MainActivity.this.f18531aD.mo8319a((C2699a) new C2699a() {
//                        /* renamed from: b */
//                        public void mo6968b() {
//                            if (MainActivity.this.f18531aD.mo8324a()) {
//                                new Handler().postDelayed(new Runnable() {
//                                    public void run() {
//                                        if (MainActivity.this.f18601n) {
//                                            MainActivity.this.f18531aD.mo8325b();
//                                        }
//                                    }
//                                }, 2000);
//                            }
//                        }
//                    });
                }
                Math.floor((double) (intValue / 6));
                Math.floor((double) (intValue % 6));
                int i = intValue % 6;
                int i2 = intValue / 6;
                MainActivity.this.f18569aq = (long) i2;
                MainActivity.this.f18574av = (long) intValue;
                MainActivity.this.f18605r.setText(String.valueOf(i2) + "." + String.valueOf(i));
                long j = (MainActivity.this.f18571as * 6) - MainActivity.this.f18574av;
                long j2 = (MainActivity.this.f18572at * 6) - MainActivity.this.f18574av;
                long j3 = (MainActivity.this.f18573au * 6) - MainActivity.this.f18574av;
                if (j <= 0) {
                    MainActivity.this.f18519R.setText("0");
                } else if (MainActivity.this.f18571as > 0) {
                    MainActivity.this.f18519R.setText(String.valueOf(j));
                }
                if (j2 <= 0) {
                    MainActivity.this.f18518Q.setText("0");
                } else if (MainActivity.this.f18576ax > 0) {
                    MainActivity.this.f18518Q.setText(String.valueOf(j2));
                }
                if (j3 <= 0) {
                    MainActivity.this.f18517P.setText("0");
                } else if (MainActivity.this.f18577ay > 0) {
                    MainActivity.this.f18517P.setText(String.valueOf(j3));
                }
                String str = String.valueOf(i2) + "." + String.valueOf(i);
                double parseDouble = ((double) MainActivity.this.f18570ar) / Double.parseDouble(str);
                if (Double.parseDouble(str) > 0.0d) {
                    MainActivity.this.f18542aO.setText(String.valueOf(String.format("%.2f", new Object[]{Double.valueOf(parseDouble)})));
                } else {
                    MainActivity.this.f18542aO.setText("0.0");
                }
                long j4 = MainActivity.this.f18528aA - MainActivity.this.f18574av;
                if (MainActivity.this.f18578az == 0) {
                    MainActivity.this.f18537aJ.setText("--");
                } else {
                    MainActivity.this.f18537aJ.setText(String.valueOf(j4));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("bhaiji", "The read failed:");
            }
        });

        database.getReference("target").addValueEventListener(new ValueEventListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Long l = (Long) dataSnapshot.getValue(Long.class);
                MainActivity.this.f18606s.setText(String.valueOf(l));
                MainActivity.this.f18578az = l.longValue();
                long j = MainActivity.this.f18528aA - MainActivity.this.f18574av;
                if (MainActivity.this.f18578az == 0) {
                    MainActivity.this.f18537aJ.setText("--");
                    MainActivity.this.f18545aR.setVisibility(8);
                    return;
                }
                MainActivity.this.f18545aR.setVisibility(0);
                MainActivity.this.f18537aJ.setText(String.valueOf(j));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("bhaiji", "The read failed:");
            }
        });

        database.getReference("totalBalls").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                MainActivity.this.f18528aA = ((Long) dataSnapshot.getValue(Long.class)).longValue();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("bhaiji", "The read failed:");
            }
        });

        database.getReference("bowlerBalls").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Long l = (Long) dataSnapshot.getValue(Long.class);
                MainActivity.this.f18529aB = l.longValue();
                String str = String.valueOf(l.longValue() / 6) + "." + String.valueOf(l.longValue() % 6);
                MainActivity.this.f18562aj.setText(str);
                if (Double.parseDouble(str) > 0.0d) {
                    double parseDouble = Double.parseDouble(String.valueOf(MainActivity.this.f18530aC)) / Double.parseDouble(String.valueOf(str));
                    if (parseDouble > 0.0d) {
                        MainActivity.this.f18565am.setText(String.valueOf(String.format("%.2f", new Object[]{Double.valueOf(parseDouble)})));
                        return;
                    }
                    MainActivity.this.f18565am.setText("-");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("bhaiji", "The read failed:");
            }
        });

        database.getReference("ballerWickets").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                MainActivity.this.f18564al.setText(String.valueOf((Long) dataSnapshot.getValue(Long.class)));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("bhaiji", "The read failed:");
            }
        });

        database.getReference("currentlyPlaying").addValueEventListener(new ValueEventListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String str = (String) dataSnapshot.getValue(String.class);
                if (str.equals("")) {
                    MainActivity.this.f18513L.setVisibility(8);
                    return;
                }
                MainActivity.this.f18513L.setVisibility(0);
                MainActivity.this.f18513L.setText(str);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("bhaiji", "The read failed:");
            }
        });

        database.getReference("team1").addValueEventListener(new ValueEventListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String str = (String) dataSnapshot.getValue(String.class);
                if (str.equals("")) {
                    MainActivity.this.f18607t.setText("--");
                } else {
                    MainActivity.this.f18607t.setText(str);
                }
                if (str.equals("")) {
                    MainActivity.this.f18523V.setText("--");
                } else {
                    MainActivity.this.f18523V.setText(str);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("bhaiji", "The read failed:");
            }
        });

        database.getReference("team2").addValueEventListener(new ValueEventListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String str = (String) dataSnapshot.getValue(String.class);
                if (str.equals("")) {
                    MainActivity.this.f18608u.setText("--");
                } else {
                    MainActivity.this.f18608u.setText(str);
                }
                if (str.equals("")) {
                    MainActivity.this.f18554ab.setText("--");
                } else {
                    MainActivity.this.f18554ab.setText(str);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("bhaiji", "The read failed:");
            }
        });

        database.getReference("bowlerName").addValueEventListener(new ValueEventListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String str = (String) dataSnapshot.getValue(String.class);
                if (str.equals("")) {
                    MainActivity.this.f18512K.setText("--");
                } else {
                    MainActivity.this.f18512K.setText(str);
                }
                if (str.equals("")) {
                    MainActivity.this.f18560ah.setText("--");
                } else {
                    MainActivity.this.f18560ah.setText(str);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("bhaiji", "The read failed:");
            }
        });

        database.getReference("ballerMaiden").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                MainActivity.this.f18563ak.setText(String.valueOf((Long) dataSnapshot.getValue(Long.class)));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("bhaiji", "The read failed:");
            }
        });

        database.getReference("player1run").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Long l = (Long) dataSnapshot.getValue(Long.class);
                MainActivity.this.f18551aY = (double) l.longValue();
                MainActivity.this.f18539aL.setText(String.valueOf(l));
                MainActivity.this.f18524W.setText(String.valueOf(l));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("bhaiji", "The read failed:");
            }
        });

        database.getReference("player2run").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Long l = (Long) dataSnapshot.getValue(Long.class);
                MainActivity.this.f18580ba = (double) l.longValue();
                MainActivity.this.f18540aM.setText(String.valueOf(l));
                MainActivity.this.f18555ac.setText(String.valueOf(l));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("bhaiji", "The read failed:");
            }
        });

        database.getReference("player1Ball").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Long l = (Long) dataSnapshot.getValue(Long.class);
                MainActivity.this.f18552aZ = (double) l.longValue();
                long longValue = l.longValue() / 6;
                long longValue2 = l.longValue() % 6;
                MainActivity.this.f18520S.setText("(" + String.valueOf(l) + ")");
                MainActivity.this.f18525X.setText(String.valueOf(l));
                if (MainActivity.this.f18552aZ > 0.0d) {
                    double d = (MainActivity.this.f18551aY / MainActivity.this.f18552aZ) * 100.0d;
                    if (d > 0.0d) {
                        MainActivity.this.f18553aa.setText(String.valueOf(String.format("%.2f", new Object[]{Double.valueOf(d)})));
                        return;
                    }
                    MainActivity.this.f18553aa.setText("--");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("bhaiji", "The read failed:");
            }
        });

        database.getReference("player2Ball").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Long l = (Long) dataSnapshot.getValue(Long.class);
                MainActivity.this.f18581bb = (double) l.longValue();
                long longValue = l.longValue() / 6;
                long longValue2 = l.longValue() % 6;
                MainActivity.this.f18521T.setText("(" + String.valueOf(l) + ")");
                MainActivity.this.f18556ad.setText(String.valueOf(l));
                if (MainActivity.this.f18581bb > 0.0d) {
                    double d = (MainActivity.this.f18580ba / MainActivity.this.f18581bb) * 100.0d;
                    if (d > 0.0d) {
                        MainActivity.this.f18559ag.setText(String.valueOf(String.format("%.2f", new Object[]{Double.valueOf(d)})));
                        return;
                    }
                    MainActivity.this.f18559ag.setText("--");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("bhaiji", "The read failed:");
            }
        });

        database.getReference("player1Fours").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                MainActivity.this.f18526Y.setText(String.valueOf((Long) dataSnapshot.getValue(Long.class)));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("bhaiji", "The read failed:");
            }
        });

        database.getReference("player2Fours").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                MainActivity.this.f18557ae.setText(String.valueOf((Long) dataSnapshot.getValue(Long.class)));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("bhaiji", "The read failed:");
            }
        });

        database.getReference("player1Sixes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                MainActivity.this.f18527Z.setText(String.valueOf((Long) dataSnapshot.getValue(Long.class)));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("bhaiji", "The read failed:");
            }
        });

        database.getReference("player2Sixes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                MainActivity.this.f18558af.setText(String.valueOf((Long) dataSnapshot.getValue(Long.class)));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("bhaiji", "The read failed:");
            }
        });

        database.getReference("bowlerBalls").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Long l = (Long) dataSnapshot.getValue(Long.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("bhaiji", "The read failed:");
            }
        });

        database.getReference("rate1New").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Long l = (Long) dataSnapshot.getValue(Long.class);
                MainActivity.this.f18546aT = String.valueOf(l);
                MainActivity.this.f18609v.setText(String.valueOf(l));
                MainActivity.this.f18535aH.startAnimation(MainActivity.this.f18533aF);
                if (MainActivity.this.f18550aX == 2) {
                    MainActivity.this.f18543aP = AnimationUtils.loadAnimation(MainActivity.this, R.anim.zoom_in);
                    MainActivity.this.f18544aQ = AnimationUtils.loadAnimation(MainActivity.this, R.anim.zoom_out);
                    MainActivity.this.f18609v.startAnimation(MainActivity.this.f18543aP);
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            MainActivity.this.f18609v.startAnimation(MainActivity.this.f18544aQ);
                        }
                    }, 200);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("bhaiji", "The read failed:");
            }
        });

        database.getReference("rate2New").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Long l = (Long) dataSnapshot.getValue(Long.class);
                Log.d("rate2New", "" + l);
                MainActivity.this.f18547aU = String.valueOf(l);
                MainActivity.this.f18610w.setText(String.valueOf(l));
                MainActivity.this.f18535aH.startAnimation(MainActivity.this.f18533aF);
                if (MainActivity.this.f18550aX == 2) {
                    MainActivity.this.f18543aP = AnimationUtils.loadAnimation(MainActivity.this, R.anim.zoom_in);
                    MainActivity.this.f18544aQ = AnimationUtils.loadAnimation(MainActivity.this, R.anim.zoom_out);
                    MainActivity.this.f18610w.startAnimation(MainActivity.this.f18543aP);
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            MainActivity.this.f18610w.startAnimation(MainActivity.this.f18544aQ);
                        }
                    }, 200);
                }
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        MainActivity.f18495aS = MainActivity.this.f18546aT + " " + MainActivity.this.f18547aU;
                        if (MainActivity.this.f18601n && defaultSharedPreferences.getBoolean("speechnew1", false)) {
                            MainActivity.this.m26404m();
                        }
                    }
                }, 1000);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("bhaiji", "The read failed:");
            }
        });

        database.getReference("fav").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                MainActivity.this.f18510I.setText((String) dataSnapshot.getValue(String.class));
                MainActivity.this.f18535aH.startAnimation(MainActivity.this.f18533aF);
                if (MainActivity.this.f18550aX == 2) {
                    MainActivity.this.f18543aP = AnimationUtils.loadAnimation(MainActivity.this, R.anim.zoom_in);
                    MainActivity.this.f18544aQ = AnimationUtils.loadAnimation(MainActivity.this, R.anim.zoom_out);
                    MainActivity.this.f18510I.startAnimation(MainActivity.this.f18543aP);
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            MainActivity.this.f18510I.startAnimation(MainActivity.this.f18544aQ);
                        }
                    }, 200);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("bhaiji", "The read failed:");
            }
        });

        database.getReference("marketRateText").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String str = (String) dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("bhaiji", "The read failed:");
            }
        });

        database.getReference("session1New").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Long l = (Long) dataSnapshot.getValue(Long.class);
                String.valueOf(l);
                MainActivity.this.f18612y.setText(String.valueOf(l));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("bhaiji", "The read failed:");
            }
        });

        database.getReference("session2New").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Long l = (Long) dataSnapshot.getValue(Long.class);
                final String valueOf = String.valueOf(l);
                MainActivity.this.f18613z.setText(String.valueOf(l));
                MainActivity.this.f18577ay = l.longValue();
                long longValue = l.longValue() - MainActivity.this.f18570ar;
                long j = (MainActivity.this.f18573au * 6) - MainActivity.this.f18574av;
                if (longValue > 0) {
                    MainActivity.this.f18514M.setText(String.valueOf(longValue));
                } else {
                    MainActivity.this.f18514M.setText("0");
                }
                if (MainActivity.this.f18577ay <= 0) {
                    MainActivity.this.f18517P.setText("0");
                } else if (j > 0) {
                    MainActivity.this.f18517P.setText(String.valueOf(j));
                } else {
                    MainActivity.this.f18517P.setText("0");
                }
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        MainActivity.f18495aS = valueOf;
                        if (!MainActivity.this.f18601n || defaultSharedPreferences.getBoolean("speechnew1", false)) {
                        }
                    }
                }, 2000);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("bhaiji", "The read failed:");
            }
        });

        database.getReference("session3").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                MainActivity.this.f18502A.setText(String.valueOf((Long) dataSnapshot.getValue(Long.class)));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("bhaiji", "The read failed:");
            }
        });

        database.getReference("session4").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Long l = (Long) dataSnapshot.getValue(Long.class);
                MainActivity.this.f18503B.setText(String.valueOf(l));
                MainActivity.this.f18576ax = l.longValue();
                long longValue = l.longValue() - MainActivity.this.f18570ar;
                long j = (MainActivity.this.f18572at * 6) - MainActivity.this.f18574av;
                if (longValue > 0) {
                    MainActivity.this.f18515N.setText(String.valueOf(longValue));
                } else {
                    MainActivity.this.f18515N.setText("0");
                }
                if (j <= 0) {
                    MainActivity.this.f18518Q.setText("0");
                } else if (MainActivity.this.f18576ax > 0) {
                    MainActivity.this.f18518Q.setText(String.valueOf(j));
                } else if (l.longValue() == 0) {
                    MainActivity.this.f18518Q.setText("0");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("bhaiji", "The read failed:");
            }
        });

        database.getReference("session5").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                MainActivity.this.f18504C.setText(String.valueOf((Long) dataSnapshot.getValue(Long.class)));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("bhaiji", "The read failed:");
            }
        });


        database.getReference("session6").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Long l = (Long) dataSnapshot.getValue(Long.class);
                MainActivity.this.f18505D.setText(String.valueOf(l));
                MainActivity.this.f18575aw = l.longValue();
                long longValue = l.longValue() - Long.parseLong(MainActivity.this.f18567ao);
                long longValue2 = l.longValue() - MainActivity.this.f18570ar;
                long j = (MainActivity.this.f18571as * 6) - MainActivity.this.f18574av;
                if (longValue2 > 0) {
                    MainActivity.this.f18516O.setText(String.valueOf(longValue2));
                } else {
                    MainActivity.this.f18516O.setText("0");
                }
                if (j <= 0) {
                    MainActivity.this.f18519R.setText("0");
                } else if (MainActivity.this.f18571as > 0) {
                    MainActivity.this.f18519R.setText(String.valueOf(j));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("bhaiji", "The read failed:");
            }
        });

        database.getReference("over1New").addValueEventListener(new ValueEventListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Long l = (Long) dataSnapshot.getValue(Long.class);
                if (l.longValue() == 0) {
                    MainActivity.this.f18506E.setVisibility(8);
                } else {
                    MainActivity.this.f18506E.setVisibility(0);
                    MainActivity.this.f18506E.setText(String.valueOf(l) + " Overs Session");
                }
                MainActivity.this.f18573au = l.longValue();
                long j = (MainActivity.this.f18573au * 6) - MainActivity.this.f18574av;
                if (j <= 0) {
                    MainActivity.this.f18517P.setText(String.valueOf(0));
                } else if (MainActivity.this.f18577ay > 0) {
                    MainActivity.this.f18517P.setText(String.valueOf(j));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("bhaiji", "The read failed:");
            }
        });


        database.getReference("commentPost").addValueEventListener(new ValueEventListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                MainActivity.this.f18509H.setText((String) dataSnapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("bhaiji", "The read failed:");
            }
        });

        database.getReference("wickets").addValueEventListener(new ValueEventListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String valueOf = String.valueOf((Long) dataSnapshot.getValue(Long.class));
                MainActivity.this.f18568ap = valueOf;
                MainActivity.this.f18604q.setText(MainActivity.this.f18567ao + "/" + valueOf);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("bhaiji", "The read failed:");
            }
        });

        database.getReference("lastSixBalls").child("ball1").addValueEventListener(new ValueEventListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String str = (String) dataSnapshot.getValue(String.class);
                if (str.equals(".")) {
                    MainActivity.this.f18582bc.setText("•");
                    MainActivity.this.f18582bc.setBackground(MainActivity.this.getResources().getDrawable(R.drawable.rounded_balls));
                } else if (str.equals("W")) {
                    MainActivity.this.f18582bc.setText(str);
                    MainActivity.this.f18582bc.setBackground(MainActivity.this.getResources().getDrawable(R.drawable.red_dot));
                } else {
                    MainActivity.this.f18582bc.setText(str);
                    MainActivity.this.f18582bc.setBackground(MainActivity.this.getResources().getDrawable(R.drawable.rounded_balls));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("bhaiji", "The read failed:");
            }
        });

        database.getReference("lastSixBalls").child("ball2").addValueEventListener(new ValueEventListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String str = (String) dataSnapshot.getValue(String.class);
                if (str.equals(".")) {
                    MainActivity.this.f18583bd.setText("•");
                    MainActivity.this.f18583bd.setBackground(MainActivity.this.getResources().getDrawable(R.drawable.rounded_balls));
                } else if (str.equals("W")) {
                    MainActivity.this.f18583bd.setText(str);
                    MainActivity.this.f18583bd.setBackground(MainActivity.this.getResources().getDrawable(R.drawable.red_dot));
                } else {
                    MainActivity.this.f18583bd.setText(str);
                    MainActivity.this.f18583bd.setBackground(MainActivity.this.getResources().getDrawable(R.drawable.rounded_balls));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("bhaiji", "The read failed:");
            }
        });

        database.getReference("lastSixBalls").child("ball3").addValueEventListener(new ValueEventListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String str = (String) dataSnapshot.getValue(String.class);
                if (str.equals(".")) {
                    MainActivity.this.f18584be.setText("•");
                    MainActivity.this.f18584be.setBackground(MainActivity.this.getResources().getDrawable(R.drawable.rounded_balls));
                } else if (str.equals("W")) {
                    MainActivity.this.f18584be.setText(str);
                    MainActivity.this.f18584be.setBackground(MainActivity.this.getResources().getDrawable(R.drawable.red_dot));
                } else {
                    MainActivity.this.f18584be.setText(str);
                    MainActivity.this.f18584be.setBackground(MainActivity.this.getResources().getDrawable(R.drawable.rounded_balls));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("bhaiji", "The read failed:");
            }
        });

        database.getReference("lastSixBalls").child("ball4").addValueEventListener(new ValueEventListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String str = (String) dataSnapshot.getValue(String.class);
                if (str.equals(".")) {
                    MainActivity.this.f18585bf.setText("•");
                    MainActivity.this.f18585bf.setBackground(MainActivity.this.getResources().getDrawable(R.drawable.rounded_balls));
                } else if (str.equals("W")) {
                    MainActivity.this.f18585bf.setText(str);
                    MainActivity.this.f18585bf.setBackground(MainActivity.this.getResources().getDrawable(R.drawable.red_dot));
                } else {
                    MainActivity.this.f18585bf.setText(str);
                    MainActivity.this.f18585bf.setBackground(MainActivity.this.getResources().getDrawable(R.drawable.rounded_balls));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("bhaiji", "The read failed:");
            }
        });

        database.getReference("lastSixBalls").child("ball5").addValueEventListener(new ValueEventListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String str = (String) dataSnapshot.getValue(String.class);
                if (str.equals(".")) {
                    MainActivity.this.f18586bg.setText("•");
                    MainActivity.this.f18586bg.setBackground(MainActivity.this.getResources().getDrawable(R.drawable.rounded_balls));
                } else if (str.equals("W")) {
                    MainActivity.this.f18586bg.setText(str);
                    MainActivity.this.f18586bg.setBackground(MainActivity.this.getResources().getDrawable(R.drawable.red_dot));
                } else {
                    MainActivity.this.f18586bg.setText(str);
                    MainActivity.this.f18586bg.setBackground(MainActivity.this.getResources().getDrawable(R.drawable.rounded_balls));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("bhaiji", "The read failed:");
            }
        });

        database.getReference("lastSixBalls").child("ball6").addValueEventListener(new ValueEventListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String str = (String) dataSnapshot.getValue(String.class);
                if (str.equals(".")) {
                    MainActivity.this.f18587bh.setText("•");
                    MainActivity.this.f18587bh.setBackground(MainActivity.this.getResources().getDrawable(R.drawable.rounded_balls));
                } else if (str.equals("W")) {
                    MainActivity.this.f18587bh.setText(str);
                    MainActivity.this.f18587bh.setBackground(MainActivity.this.getResources().getDrawable(R.drawable.red_dot));
                } else {
                    MainActivity.this.f18587bh.setText(str);
                    MainActivity.this.f18587bh.setBackground(MainActivity.this.getResources().getDrawable(R.drawable.rounded_balls));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("bhaiji", "The read failed:");
            }
        });

    }


    public void m26404m() {
        this.f18602o.speak(f18495aS, 0, (HashMap) null);
    }

    private Timer timer;
    private TimerTask timertask;

    public void mo17774l() {
        this.timer.cancel();
        this.timer = null;
    }

    public void onPause() {
        mo17774l();
        f18601n = false;
        database.goOffline();
        this.f18550aX = 0;
        super.onPause();
    }

    public void onPostResume() {
        super.onPostResume();
        f18601n = true;
        timertask = new TimerTask() {
            public void run() {
                runOnUiThread(new Runnable() {
                    public void run() {
                    }
                });
            }
        };
        mo17773k();
    }

    public void mo17773k() {
        this.f18601n = true;
        if (timer == null) {
            timer = new Timer();
            timer.scheduleAtFixedRate(timertask, 1000, 20000);
        }
    }

    @Override
    public void onInit(int i) {
        if (i == 0) {
            int language = this.f18602o.setLanguage(new Locale("hin", "IND"));
            if (language == -1 || language == -2) {
                Log.e("TTS", "This Language is not supported");
            } else {
                if (PreferenceManager.getDefaultSharedPreferences(this).getBoolean("speechnew1", false)) {
                }
            }
        } else {
            Log.e("TTS", "Initilization Failed!");
        }
    }

//    private void getData() {
//        requestQueue = Volley.newRequestQueue(getApplicationContext());
//        StringRequest request = new StringRequest(1, "http://matalanghana.com/cricklineapi/fetch_recent_matches.php", new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                try {
//                    JSONObject jSONObject = new JSONObject(response);
//                    if (Integer.parseInt(jSONObject.getString("count")) > 0) {
//                        JSONArray jSONArray = jSONObject.getJSONArray("matches");
//                        Log.d("sizeji", " " + jSONArray.length());
//                        Log.d("sizeji", " " + jSONArray);
//                        for (int i = 0; i < jSONArray.length(); i++) {
//                            JSONObject jSONObject2 = (JSONObject) jSONArray.get(i);
//                            String string = jSONObject2.getString("match_type");
//                            String string2 = jSONObject2.getString("match_name");
//                            String string3 = jSONObject2.getString("stadium");
//                            String string4 = jSONObject2.getString("date");
//                            String string5 = jSONObject2.getString("team1");
//                            String string6 = jSONObject2.getString("team2");
//                        }
//                        return;
//                    }
//                } catch (JSONException e) {
//                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(MainActivity.this, "RealError", Toast.LENGTH_SHORT).show();
//            }
//        });
//        requestQueue.add(request);
//    }
//
//    private void fetch_live_matches() {
//        requestQueue2 = Volley.newRequestQueue(getApplicationContext());
//        StringRequest request = new StringRequest(1, "http://matalanghana.com/cricklineapi/fetch_live_matches.php", new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                try {
//                    JSONObject jSONObject = new JSONObject(response);
//                    if (Integer.parseInt(jSONObject.getString("count")) > 0) {
//                        JSONArray jSONArray = jSONObject.getJSONArray("matches");
//                        Log.d("sizeji", " " + jSONArray.length());
//                        Log.d("sizeji", " " + jSONArray);
//
//                        for (int i = 0; i < jSONArray.length(); i++) {
//                            JSONObject jSONObject2 = (JSONObject) jSONArray.get(i);
//                            String string = jSONObject2.getString("match_type");
//                            String string2 = jSONObject2.getString("match_name");
//                            String string3 = jSONObject2.getString("stadium");
//                            String string4 = jSONObject2.getString("date");
//                            String string5 = jSONObject2.getString("team1");
//                            String string6 = jSONObject2.getString("team2");
//                            String string7 = jSONObject2.getString("key");
//                            String string8 = jSONObject2.getString("url1");
//                            String string9 = jSONObject2.getString("url2");
//                            key = string7;
//                            if (string8.equals("")) {
//                                string8 = "http://matalanghana.com/cricklineapi/logo.png";
//                            }
//                            if (string9.equals("")) {
//                                string9 = "http://matalanghana.com/cricklineapi/logo.png";
//                            }
//                        }
//                        return;
//                    }
//                } catch (JSONException e) {
//                    Toast.makeText(MainActivity.this, "Error2", Toast.LENGTH_SHORT).show();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(MainActivity.this, "RealError2", Toast.LENGTH_SHORT).show();
//            }
//        });
//        requestQueue2.add(request);
//    }
//
//    String key;
//
//    private void getLiveMatch() {
//        requestQueue3 = Volley.newRequestQueue(getApplicationContext());
//        StringRequest request = new StringRequest(1, "http://matalanghana.com/cricklineapi/fetch_single_recent_match_all_data.php", new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                try {
//                    JSONObject jSONObject = new JSONObject(response);
//                    Log.d("jiji", "" + jSONObject);
//                    JSONArray jSONArray = jSONObject.getJSONArray("bettingscore");
//
//                    for (int i = 0; i < jSONArray.length(); i++) {
//                        JSONObject jSONObject2 = (JSONObject) jSONArray.get(i);
//
//                    }
//
//                    JSONArray jSONArray2 = jSONObject.getJSONArray("bowlingscore");
//                    for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
//                        JSONObject jSONObject3 = (JSONObject) jSONArray2.get(i2);
//
//                    }
//
//                    JSONObject jSONObject4 = jSONObject.getJSONArray("comentry").getJSONObject(0);
//                    String string = jSONObject4.getString("teamfallwicket");
//                    String string2 = jSONObject4.getString("teamscore");
//
//                } catch (JSONException e) {
//                    Toast.makeText(MainActivity.this, "Error4", Toast.LENGTH_SHORT).show();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(MainActivity.this, "RealError4", Toast.LENGTH_SHORT).show();
//            }
//        });
//        requestQueue3.add(request);
//    }


}