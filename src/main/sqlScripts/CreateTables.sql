Create table WPCs (
    valueStream VARCHAR(25),
    productionLine VARCHAR(50),
    productType VARCHAR(40),
    workPieceCarrierNumber int,
    PRIMARY KEY (valueStream , productionLine , productType, workPieceCarrierNumber)
);

Create table WPCCombos (
    valueStream VARCHAR(25),
    productionLine VARCHAR(50),
    productType VARCHAR(40),
    PRIMARY KEY (valueStream , productionLine , productType)
);