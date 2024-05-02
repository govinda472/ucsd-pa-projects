# Load necessary libraries
library(nnet)  # For multinomial logistic regression
library(dplyr) # For data manipulation

# Read the data
customer_data <- read.csv("smartphone_customer_data.csv")

# Calculate changes in market share from three years ago to last year
market_share_changes <- function(data, years_past) {
  data_years_ago <- filter(data, years_ago == years_past)
  data_last_year <- filter(data, years_ago == 1)
  
  share_years_ago <- prop.table(table(data_years_ago$brand))
  share_last_year <- prop.table(table(data_last_year$brand))
  
  share_change <- round(share_last_year - share_years_ago, 3)
  return(share_change)
}

share_changes <- market_share_changes(customer_data, 3)
print("Question 1 - Market Share Changes:")
print(share_changes)

# Prepare data for modeling
mdat1 <- filter(customer_data, years_ago == 1)
mdat1$brand <- relevel(factor(mdat1$brand), ref = "huawei")

# Fit multinomial logit model for cohort 1 using only brand intercepts
mlogit_model1 <- multinom(brand ~ 1, data = mdat1)
coefficients1 <- coef(mlogit_model1)
print("Question 2 - Coefficients for Model 1 (Intercepts only):")
print(coefficients1)

# Fit multinomial logit model including price for cohort 1
mlogit_model2 <- multinom(brand ~ price + 1, data = mdat1)
coefficients2 <- coef(mlogit_model2)
print("Question 4 - Coefficients for Model 2 (Including Price):")
print(coefficients2)

# Extract price coefficients specifically
price_coefficient_mlogit1 <- coefficients2['price',]
print("Question 8 & 9 - Price Coefficient for Model 2:")
print(price_coefficient_mlogit1)






