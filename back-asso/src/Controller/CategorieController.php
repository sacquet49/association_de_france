<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\HiddenType;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\IsGranted;
use FOS\RestBundle\View\View;
use App\Entity\Catergorie;

/**
 * Brand controller.
 *
 * @Route("/api")
 */
class CategorieController extends AbstractController
{
    /**
     * @Route("/categories", methods={"GET"})
     *
     * @return array
     */
    public function getCategorieAction()
    {
        $entityManager = $this->getDoctrine()->getManager();
        $categories = $entityManager->getRepository(Catergorie::class)->findAll();
        return View::create($categories, Response::HTTP_OK , []);
    }
}
