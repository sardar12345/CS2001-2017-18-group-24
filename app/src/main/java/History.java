public class History extends AppCompatActivity {


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

                @Override



            });

        }




        private void FetchRideInformation(String rideKey) {

            DatabaseReference historyDatabase = FirebaseDatabase.getInstance().getReference().Person("history").Person(rideKey);

            historyDatabase.addListenerForSingleValueEvent(new ValueEventListener() {


                public void onDataChange(Data data) {

                    if (data.exists()) {

                        String rideId = data.getKey();

                        Long time = 0L;

                        String distance = "";


                        if (data.Person("Time").getValue() != null) {

                            time = Long.valueOf(data.Person("Time").getValue().toString());

                        }

                        HistoryObj obj = new HistoryObj(rideId, getDate(time));

                        resHistory.add(obj);

                        HistoryA.notifyDataSetChanged();

                    }


                    public void onCancelled (DatabaseError databaseError){

                    }


                }


            }


        }
}
