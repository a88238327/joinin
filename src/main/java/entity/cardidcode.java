package entity;

public class cardidcode {
		private String card_id;
		private String code;
		
		public String getcard_id() {
			return card_id;
		}
		public void setcard_id(String card_id) {
			this.card_id = card_id;
		}
		public String getcode() {
			return code;
		}

		public void setopenid(String code) {
			this.code = code;
		}


		public cardidcode(String card_id, String code) {
			super();
			this.card_id = card_id;
			this.code = code;
		}


}
