 public void geocode() {
        Geocoder geoCoder = new Geocoder(getApplicationContext(), Locale.getDefault());
        List<Address> address = null;

        if (geoCoder != null) {
            try {
                address = geoCoder.getFromLocation(lat, lon, 1);
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            if (address.size() > 0) {
                postcode = address.get(0).getPostalCode();
            }