const express = require('express');

const router = express.Router();

router.get('/', (req, res) => {
    res.sendFile(path.join(__dirname, '../test.html'));
})



module.exports = router;