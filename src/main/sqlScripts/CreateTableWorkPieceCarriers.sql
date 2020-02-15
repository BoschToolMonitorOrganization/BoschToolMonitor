CREATE TABLE WorkPieceCarriers (
    author VARCHAR(255),
    revisionNumber VARCHAR(25),
    revisionReason VARCHAR(500),
    fileType VARCHAR(20),
    workPieceCarrierNumber VARCHAR(4),
    valueStream VARCHAR(25),
    productionLine VARCHAR(50),
    productType VARCHAR(40),
    reasonForChange VARCHAR(500),
    reasonCategory VARCHAR(20),
    toolLifeAchieved BOOLEAN,
    locationRepairTicket VARCHAR(50),
    downTimeImpact VARCHAR(30),
    PRIMARY KEY (workPieceCarrierNumber , valueStream , productionLine , productType)
);