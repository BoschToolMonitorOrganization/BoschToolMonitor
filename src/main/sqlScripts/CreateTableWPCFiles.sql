Create table WPCFiles (
    workPieceCarrierNumber VARCHAR(4),
    valueStream VARCHAR(25),
    productionLine VARCHAR(50),
    productType VARCHAR(40),
    fileName VARCHAR(50),
    fileType VARCHAR(30),
    data mediumblob,
    
    PRIMARY KEY (workPieceCarrierNumber , valueStream , productionLine , productType, fileType, fileName)
);