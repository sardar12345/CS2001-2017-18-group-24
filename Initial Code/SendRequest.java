private void sendRequest() {
        String origin = etOrigin.getText().toString();
        String destination = etDestination.getText().toString();
        if (origin.isEmpty()) {
            Toast.makeText(this, "Start location will be set to Current location", Toast.LENGTH_SHORT).show();
            origin = postcode;
        }
        if (destination.isEmpty()) {
            Toast.makeText(this, "Please enter destination address", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            new DirectionFinder(this, origin, destination).execute();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }