<?php
namespace App\Controller;

use App\Entity\Association;
use App\Entity\Statistique;
use App\Entity\WaldecAssociation;
use App\Repository\WaldecAssociationRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\HiddenType;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\IsGranted;
use FOS\RestBundle\View\View;
use App\Repository\AssociationRepository;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Security;

/**
 * Brand controller.
 *
 * @Route("/api")
 */
class AssociationController extends AbstractController
{
    /**
     * @Route("/associations", methods={"GET"})
     *
     * @return array
     */
    public function getAssociationsAction(Request $request, AssociationRepository $ar)
    {
        $nom =  $request->query->get('nom');
        $codesPostaux =  $request->query->get('codesPostaux');
        $categorie =  $request->query->get('categorie');

        $associations = $ar->findAssociationByCriteria($codesPostaux, $nom, $categorie);
        return View::create($associations, Response::HTTP_OK , []);
    }

    /**
     * @Route("/associations_waldec", methods={"GET"})
     *
     * @return array
     */
    public function getAssociationsWaldecAction(Request $request, WaldecAssociationRepository $war)
    {
        $nom =  $request->query->get('nom');
        $codesPostaux =  $request->query->get('codesPostaux');
        $categorie =  $request->query->get('categorie');

        $associations = $war->findAssociationByCriteria($codesPostaux, $nom, $categorie);
        return View::create($associations, Response::HTTP_OK , []);
    }

    /**
     * @Route("/association/{id}", methods={"GET"})
     *
     * @return array
     */
    public function getOneAssociationAction(Request $request)
    {
        $entityManager = $this->getDoctrine()->getManager();
        $association = $entityManager->getRepository(Association::class)->find($request->get('id'));
        return View::create($association, Response::HTTP_OK , []);
    }

    /**
     * @Route("/association_waldec/{id}", methods={"GET"})
     *
     * @return array
     */
    public function getOneAssociationWaldecAction(Request $request)
    {
        $entityManager = $this->getDoctrine()->getManager();
        $association = $entityManager->getRepository(WaldecAssociation::class)->find($request->get('id'));
        return View::create($association, Response::HTTP_OK , []);
    }

    /**
     * @Route("/auth/association_waldecs/stat", methods={"GET"})
     * @Security("is_granted('ROLE_USER_CLASSIC')")*
     */
    public function getStatAssociationWaldecAction()
    {
        $entityManager = $this->getDoctrine()->getManager();
        $association = $entityManager->getRepository(Statistique::class)->findAll();
        return View::create($association, Response::HTTP_OK , []);
    }
}
