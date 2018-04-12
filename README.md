# SoftwareII-Project" Online Market"
Requirements backlog:

1-	User should be able to register in the online market. Each user should be identified by username or email. Each user should provide a password which will be used later to login

2-	User should be able to login into the online market by providing username/email and password. 

3-	User should be able to add a new store to the online market. The online store information should include the store location (onsite or online), store name, store type (sports, entertainment, clothes, technology, …, etc) and any other relative information. The store will not be added until the online store administrator accepts it.

4-	The administrator should be able to add new products to the system. The products info should include product name, price range, category, …,etc. Once the product is added it should be available to the store owner to add it.

5-	The store owner (The user who had a store) should be able to add new products to a store (if he had one). The product should be existed in the system (added firstly by the system administrator). The store owner should determine the available quantity he had. The store owner should put a price to the product within the price range of this product (specified previously by the store administrator)

6-	User should be able to buy a product in the system. The buying process start by selecting the target product (which the user wants to buy), select the required amounts, select the shipping address, agree on the agreement (if exist for the product), confirm the provided information.

EDIT: The total price of each transactions should be decreased 15% if the user is store owner, 10% if the user buy 2 items of the same products and 5% if this is the first time the user buy a product. Please be noted that these conditions may be all met in a time so for example if the user who is going to buy the product is a store owner, first buy transaction for him, buy +2 items from the same product so the price of the product should be decreased by 30%

7-	The store owner should be able to see some live statistics about the store. These statistics like number of users viewed the store’s products, number of user buy a store’s produce, The current sold out products ,…, etc. These statistics should be refreshed every 1 minute.

8-	The store owner should be able to customize his store administration panel. The panel consists of different panels each panel represent a specific statistic. The store owner should be able to customize the store administration panel by (add/remove) some panels.

9-	Administrator should be able to add new statistic to the system. The statistic focus on specific entity (users, products, offers) and applies specific function on it (sum, average, max, min).

10-	Administrator should be able to add new brands to the system. The brand info like brand name, brand category, …, etc. The store owner must assign a brand for each added product

11-	Store owner should be able to add new collaborators to his store. The collaborator should be able to do the same functionalities as the store owner (i.e. add product to store, add offer, …,etc)

12-	The original store owner should be able to check history of actions happened in the store. The original store owner is the one who firstly created the store. The system should provide a page for the original store owner in this page all actions should be shown, and the original store owner should be able to undo any action. The possible actions that should be saved are add/edit/delete product and add/edit/delete offer.
