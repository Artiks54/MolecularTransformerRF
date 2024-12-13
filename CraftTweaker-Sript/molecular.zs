import mods.mrf.molecular;

molecular.addRecipeMolecular(<input>, <output>, energy);
molecular.addRecipeMolecularInput(<input1>, <input2>, <output>, energy);
molecular.addRecipeMolecularOutput(<input>, <output1>, <output2>, energy);

//EXAMPLE:
//molecular.addRecipeMolecular(<minecraft:stone>, <minecraft:diamond_block>*2, 1000000000);
//Input : <minecraft:stone>
//Output : <minecraft:diamond_block>*2 -> Diamond_block, Count 2;
//Energy : 1000000000 -> 1 billion
//Maximum energy MAX java Long value
//9,223,372,036,854,775,806