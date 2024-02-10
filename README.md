
# eComm for Boring Inc.
Your task is to calculate profit margins for orders in eComm web application. You got orders through CSV file with following format:

- full_name,shirt_size,with_design,with_hoodie,payment



        full_name - name of the customer represents customer name

        shirt_size - string value representing size of the shirt

        with_design - boolean representing if customer wants design to be applied on the shirt front

        with_hoodie - boolean representing if customer wants hoodie to be attached to the shirt

        payment - credit card used for processing payment

## Instructions
Here are important information about the product:

- Customers are paying 40 BAM per shirt regardless their selection of design and hoodie.
- Base price eComm is paying supplier for a shirt is 14 BAM
- If there is hoodie attached it increases prices for 3 BAM
- If design is applied it increases prices for 2 BAM


Regarding payment you should know following information:

- If customer pay with wallet, there are no additional costs;
- If customer pay with bankcard, eComm has to pay 5% of the transaction to the bank
- If customer pay with visa, eComm has to pay 2% of the transaction to the bank
- If customer pay with mastercard, eComm has to pay 3% of the transaction to the bank
- Any other payment method eComm has to pay 10% of the transaction



### Tasks
Your task is to provide following reports:

- Total revenue of the eComm;
- Total profit of the eComm;
- Profit per shirt size;
- Implement payment using Strategy pattern;


        - Threads should be used for generating reports;
