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


        }

}
