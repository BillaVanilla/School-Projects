using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.EventSystems;

public class CardDisplay : MonoBehaviour
{
    public List<Card> cardDisplay = new List<Card>();
    public int displayId;

    public int id;
    public string cardName;
    public int cost;
    public int power;
    public string cardDescription;

    public Text nameText;
    public Text costText;
    public Text powerText;
    public Text DescriptionText;
    // Start is called before the first frame update
    void Start()
    {
        cardDisplay[0] = CardDataBase.cardList[displayId];

    }

    // Update is called once per frame
    void Update()
    {
        id = cardDisplay[0].id;
        cardName = cardDisplay[0].cardName;
        cost = cardDisplay[0].cost;
        power = cardDisplay[0].power;
        cardDescription = cardDisplay[0].cardDescription;

        nameText.text = " " + cardName;
        costText.text = " " + cost;
        powerText.text = " " + power;
        DescriptionText.text = " " + cardDescription;
    }
}
