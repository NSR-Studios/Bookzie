# CampusTextbookConnect

Unit 8: Group Milestone - README Example
===

:::info
**Below is an example** of what your **Group Project README** should include and how it should be structured for the **Unit 8 Group Milestone Submission**.
:::

# TUNIN

## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)

## Overview
### Description
Tracks what music an individual listens to, and pairs them with others based on that music. Could be potentially used as a dating app, or just meeting new friends with similar music tastes.

### App Evaluation
- **Category:** Education
- **Mobile:** This app would be primarily developed for mobile but would perhaps be just as viable on a computer, such as tinder or other similar apps. Functionality wouldn't be limited to mobile devices, however mobile version could potentially have more features.
- **Story:** Analyzes users music choices, and connects them to other users with similar choices. The user can then decide to message this person and befriend them if wanted.
- **Market:** Any individual could choose to use this app, and to keep it a safe environment, people would be organized into age groups.
- **Habit:** This app could be used as often or unoften as the user wanted depending on how deep their social life is, and what exactly they're looking for.
- **Scope:** First we would start with pairing people based on music taste, then perhaps this could evolve into a music sharing application as well to broaden its usage. Large potential for use with spotify, apple music, or other music streaming applications.

## Product Spec
### 1. User Stories (Required and Optional)

**Required Must-have Stories**

* User logs in to access the account
* User can post a textbook with image, price & details
* User can view book details (price,name etc) and compare prices from seller, Amazon/Google
  
  https://developers.google.com/books
  https://openlibrary.org/dev/docs/api/books
* User can view same textbooks from different sellers (RecyclerView)
* User can search for speific textbooks through ISBN/Title
* User can request to purchase textbook
* Seller can select buyer from list (users who requested that textbook)
* Implement Google MAPs API to determine meeting location of buyer and seller 
  
  https://developers.google.com/maps
* Buyer and Seller can reschedule/relocate/cancel transaction 
* User can view seller profiles (show ratings and what they are currently selling)
* User can view pending/completed transactions 

**Optional Nice-to-have Stories**

* Connection to Venmo
 
 https://stripe.com/docs/payments/payment-links/api
* Expand the scope of app beyond textbooks 
* User rates sellers
* Q/A on Seller Posts

### 2. Screen Archetypes

* Login - User logs into their account
* Register - User creates their own account
* Profile Screen 
   * Allows user to upload a photo and fill in information about them (Name, Major, Class of)
   * Shows a complete list of past and current listings
   * Optional: shows user's rating from previously completed transactions
* Create Listing Screen.
   * Allows user to be able to upload photos of the item being sold, list important information (ISBN, price, book dimensions, number of pages, class book is relevant to). 
   * Show price of the textbook at online retailers (Amazon/Google)
* Individual Listing Screen
   * Lets user view the item being sold showing the important information (ISBN, price, book dimensions, number of pages, class book is relevant to). 
   * Compares the price listed by the seller with the prices listed on online retailers (Amazon/Google)
   * The user is able to request the book for purchase
   * Optional: The user is able to ask questions the seller can respond to about the listing
* All Listing Screen
   * Lets user view the list of items being sold 
   * Each listing shows its name, small image of the item, the seller's username/name, and price of the item being sold
   * User can filter through the list by price (low to high, high to low)
   * User can search through the list by name, or ISBN
   * Optional: User can filter through the list by seller rating (above # stars)
* All Transaction Screen 
   * User can see a list of pending and completed(bought/sold/cancelled) tranactions
   * User can filter through the list by pending or completed (bought/sold/cancelled) tranactions, by name, by price
   * Upon selecting a transaction a detailed view of the transaction page will appear
* Single Transaction Screen
   * User can see the details of the item being sold (price, ISBN, page count)
   * User can see the list of users that requested to purchase the listing item (Buyer name, time requested)
   * Optional: User can select the form of payment for transaction (Cash/Venmo)
   * Set meeting location from a list of buildings on campus (campus center, bookstore, coffee shop, library, etc)
* Meeting Confirmation Screen
   * User can see the details of the meet up for listing purchase (time, location, buyer information, seller information, item being sold, price)
   * User should have the option to reschedule the meeting to a different location on campus
   * User should have the option to reschedule the meeting to a different time
* Optional: Settings Screen
   * User should be able to change user profile details (Name, Major, Class of, photo)
   * User can change view preferences between dark/light mode
   * Optional: User can change app theme by selecting different colors

### 3. Navigation

**Tab Navigation** (Tab to Screen)

* Listing Screen
* New Listing Screen
* Transaction Listing Screen
* Profile Screen

Optional:
* Settings Screen

**Flow Navigation** (Screen to Screen)
* Forced Log-in -> Account creation if no log in is available
* All Listing -> Jumps to Individual Listing
* All Transaction -> Jumps to Single Transaction
* Single Transaction -> Jumps to Meeting Confirmation
* Profile -> Text field to be modified. 
* Profile (Not Current Cser) -> Jumps to Individual Listing
* Profile (Current User) -> Jumps to Single Transaction
* Optional: Settings -> Toggle settings

## Wireframes
<img src="https://i.imgur.com/9CrjH1K.jpg" width=800><br>

### [BONUS] Digital Wireframes & Mockups
<img src="https://i.imgur.com/lYHn37F.jpg" height=200>

### [BONUS] Interactive Prototype
<img src="https://i.imgur.com/AiKfE5g.gif" width=200>
