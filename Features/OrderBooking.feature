Feature: Perform order booking

Scenario: Perform order booking

Given Open the url
When Validate that cart should be empty and user is not logged in
When Verify Search box is performing perfectly
When Perform multiple searches and add product to cart from each search.
Then All products will be added to cart properly
When Proceed to checkout
Then The added products are correctly displayed and amounts are correct
When Perform increase and decrease of product quantity should work perfectly
When Performing add or remove products functionality should work perfectly
When Now login and complete the checkout and save order id/details
Then The saved order details should display properly in Order history and details section
Then  Log out and validate that the user is logged out


