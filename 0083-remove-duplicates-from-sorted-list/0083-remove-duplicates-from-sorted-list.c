struct ListNode* deleteDuplicates(struct ListNode* head) {
    struct ListNode *ptr, *cpt, *next;
    struct ListNode *first = head;

    if (head == NULL)
        return NULL;

    ptr = head;

    while (ptr != NULL) {
        cpt = ptr->next;

        while (cpt != NULL && cpt->val == ptr->val) {
            next = cpt->next;
            free(cpt);
            cpt = next;
        }

        ptr->next = cpt;
        ptr = cpt;
    }

    return first;
}