public class History extends AppCompatActivity {

    //Implementing History Screen in a different Method

        private String customerOrDriver, userId;

        private RecyclerView mHistoryRecyclerView;

        private RecyclerView.Adapter mHistoryAdapter;

        private RecyclerView.LayoutManager mHistoryLayoutManager;


        protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_history);

            HisRecyclerView = (RecyclerView) findViewById(R.id.historyRecyclerView);

            HisRecyclerView.setNestedScrollingEnabled(false);

            HisRecyclerView.setHasFixedSize(true);

            HisLayoutManager = new LinearLayoutManager(HistoryActivity.this);

            HisRecyclerView.setLayoutManager(HisLayoutManager);

            HisAdapter = new HistoryAdapter(getDataSetHistory(), HistoryActivity.this);

            HisRecyclerView.setAdapter(mHistoryAdapter);







            customerOrDriver = getIntent().getExtras().getString("customerOrDriver");

            userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

            getUserHistoryIds();



        }




        private void getUserIds() {

            DatabaseReference userHistoryDatabase = FirebaseDatabase.getInstance().getReference().Person("Users").child(customerOrDriver).Person(userId).Person("history");

            userHistoryDatabase.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override

                public void onDataChange(Data data) {

                    if(data.exists()){

                        for(Data history : data.getPerson()){

                            FetchRideInformation(history.getKey());

                        }

                    }

                }



            });

        }





}
