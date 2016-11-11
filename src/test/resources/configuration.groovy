
tranzaxis {
    connectionTimeout = 100000
    requestTimeout = 200000
}

// Global feature blacklist (overwritten by environment)
// Specifications and features tagged with this will be skipped
blacklist = ['example.delete_me2']
countries = ['us', 'uk']


generalNextGen {
    us {
        statementDay = 1
    }
    eu {
        statementDay = 4
    }
}


general {
    holidayCalendar {
        GB = "UK Holidays and Non-working days"
        DE = "DE Holidays and Non-working days"
        US = "US Holidays and Non-working days"
    }

    statementDay = 1
    statementDayUK = 4

    klarnaAccount {
        uk {
            mpFloor = 5
            //Threshold values for 366 days
            interestLowerThanMfc {
                minimumOrderAmount = 89.80
                maximumOrderAmount = 150.00
            }
            //Threshold values for 366 days
            interestHigherThanMfc {
                minimumOrderAmount = 174.00
                maximumOrderAmount = 1000.00 //Not really, there is no maximum
            }
        }
        us {
            mpFloor = 25
        }

        pixCycles = 3
        minimumInstalment = 25
        overdueFeeAmt = 25
        transactionFeeAmt = 25
        overdueFeeAmtUpperThreshold = 25
        overdueFeeAmtLowerThreshold = 1
        defaultNumberOfCycles = 6
        orderApr = 0.0099
        minimumFinanceCharge = 2
        accountAprUS = 0.1999
        accountAprUK = 0.189
        cartAmountRange {
            lessThanMpIncludingMfc {
                minimum = 1 * 6
                maximum = 24.99 * 6
            }
            interestHigherThanMfc {
                minimum = 430 * 6
                maximum = ((Math.abs(new Random().nextInt()) % 1000) + minimum) * 6
            }
            interestLowerThanMfc {
                minimum = 25 * 6
                maximum = 397 * 6
            }
            interestLowerThanMfcUK {
                minimum = 10
                maximum = 50
            }
            interestHigherThanMfcUK {
                minimum = 50
                maximum = ((Math.abs(new Random().nextInt()) % 1000) + minimum) * 6
            }
        }
        instalmentRange {
                lower {
                    minimum = 0
                    maximum = 0.99
                }
                lessThanMpIncludingMfc {
                    minimum = 2.02
                    maximum = 24.99
                }
                lessThanMpExcludingMfc {
                    minimum = 0
                    maximum = 22.99
                }
                upper {
                    minimum = 25
                    maximum = (Math.abs(new Random().nextInt()) % 1000) + minimum
                }

                interestLowerThanMfc {
                    // Installments amounts for captures created on the 2nd
                    // that accumulate interest lower than 2 USD on next SD
                    minimum = 2
                    maximum = 397
                }
                interestHigherThanMfc {
                    minimum = 430 // 398
                    maximum = (Math.abs(new Random().nextInt()) % 1000) + minimum
                }
        }
    }
}

environments {
    'tx-staging-us' {
        locale = "en_US"
        currency = "USD"
        countries = ['us']
        region = 'us'

        klarnaAccount = [
                klarnaAccountParentId: "1177"
        ]

    }
    'tx-staging-eu' {

        locale = "en_GB"
        currency = "GBP"
        countries = ['uk']
        region = 'eu'

        klarnaAccount = [
                klarnaAccountParentId: "1185"
        ]
    }
    'tx-qa' {
        locale = "en_GB"
        currency = "GBP"
        countries = ['uk']
        region = 'eu'

        klarnaAccount = [
            klarnaAccountParentId: "1110"
        ]

    }
    'tx-dev' {
        locale = "en_US"
        currency = "USD"
        countries = ['us']
        region = 'us'

        klarnaAccount = [
                klarnaAccountParentId: "1177"
        ]

    }
}

conditions{
    us {
        latefee = (1..55)
        interest = (1..38)
        paymentallocation = (1..23)
        minimumpayment = [1, 2, 3, 4, 7]
        pyramidingfees = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]
        refund = (1..3)
        dispute = (1..8)
        credit = (1..3)
    }
    eu {
        pix = (1..19)+ (63..86) + [28,31,32,35,36,40,41,42,46,50,54,55,56,101,421,422,561]

    }
}